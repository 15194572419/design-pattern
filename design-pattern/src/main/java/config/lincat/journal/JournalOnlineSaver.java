package config.lincat.journal;

import java.io.*;
import java.util.ArrayList;

/**
 * 系统上线后的保存控制台输出类
 */
final class JournalOnlineSaver {

    /**
     * 启动系统上线日志功能：可以控制台输出重定向到日志
     */
    boolean openOnlineSave(){
        if(JournalConfig.isOpenOnlineSaver){
            //将系统的控制台输出重定向到临时文件中
            messageDirectInit();
            //设置并开启线上转存线程
            setAndStartOnlineSaveThread();

            Journal.LOG("linCat-journal-saver has been started");
            return true;
        }else {
            Journal.WRONG("linCat-journal-saver can't start please check option OpenOnlineSaver");
            return false;
        }

    }

    /**
     * 信息重定向初始化
     */
    private void messageDirectInit(){
        try {
            System.setOut(new PrintStream("linCatMessageJournal.txt"));
            System.setErr(new PrintStream("linCatErrorJournal.txt"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 设置并开启线上转存线程
     */
    private void setAndStartOnlineSaveThread(){
        //定时读取临时文件中的内容并做处理
        Thread onlineSaver = new Thread(()->{
            try {
                while (true){
                    Thread.sleep(1000*60*JournalConfig.onlineRedirectCircle);
                    //消息转存
                    messageRedirect();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        onlineSaver.setDaemon(true);
        onlineSaver.start();
    }
    /**
     * 消息转存
     */
    private void messageRedirect(){
        //处理异常日值转存
        dealExceptionJournal();
        //处理消息日志转存
        dealMessageJournal();

    }

    /**处理消息类型日志*/
    private void dealMessageJournal(){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("linCatMessageJournal.txt"));
            String content = "";
            //处理消息文件的内容
            while ((content=bufferedReader.readLine())!=null){
                JournalSaver.SaveJournal(new Journal.JournalTip(content,JournalTipType.MESSAGE,false));
            }
            bufferedReader.close();
            //处理消息文件的内容之后，将临时消息文件的内容清空
            BufferedWriter clearTempJournal = new BufferedWriter(new FileWriter("linCatMessageJournal.txt",false));
            clearTempJournal.write("");
            clearTempJournal.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**处理异常和错误类型日志*/
    private void dealExceptionJournal(){
        try{
            //处理错误文件的内容
            BufferedReader bufferedReader = new BufferedReader(new FileReader("linCatErrorJournal.txt"));
            String content ="";
            ArrayList<StringBuilder> stringBuilders = new ArrayList<>();
            StringBuilder stringBuilder = null;
            while ((content=bufferedReader.readLine())!=null){
                content = content.trim();
                if(content.substring(0,4).equals("java")||content.substring(0,9).equals("Exception")){
                    if(stringBuilder!=null){
                        stringBuilders.add(stringBuilder);
                    }
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(content);
            }
            bufferedReader.close();
            //先清空临时异常文件的内容清空
            BufferedWriter clearTempJournal = new BufferedWriter(new FileWriter("linCatErrorJournal.txt",false));
            clearTempJournal.write("");
            clearTempJournal.close();
            //再将日志内容转存到本地日志
            for (StringBuilder builder : stringBuilders) {
                //异常
                if (builder.toString().substring(0, 4).equals("java")) {
                    JournalSaver.SaveJournal(new Journal.JournalTip(builder.toString(), JournalTipType.EXCEPTION, false));
                } else {//错误
                    JournalSaver.SaveJournal(new Journal.JournalTip(builder.toString(), JournalTipType.WRONG, false));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
