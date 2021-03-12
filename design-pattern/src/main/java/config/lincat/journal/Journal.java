package config.lincat.journal;

import java.time.LocalDateTime;

/**
 * @name linCat日志类
 * @author 帆哥续写辉煌
 * @description 静态方法可保证线程安全，但性能不如单例模式方法
 */
public final class Journal {

    /**
     *打印一条含参消息
     * @param content
     */
    public static void LOG(String content){
        JournalTip journalTip = new JournalTip(content,JournalTipType.MESSAGE,true);
        JournalSaver.SaveJournal(journalTip);
        //如果开启了线上日志转存功能则不向控制台打印
        if(!JournalConfig.isOpenOnlineSaver){
            System.out.println(journalTip.getTip());
        }


    }

    /**
     * 打印一条空消息，内容设置为null
     */
    public static void LOG(){
        JournalTip journalTip = new JournalTip("null",JournalTipType.MESSAGE,true);
        JournalSaver.SaveJournal(journalTip);
        //如果开启了线上日志转存功能则不向控制台打印
        if(!JournalConfig.isOpenOnlineSaver){
            System.out.println(journalTip.getTip());
        }
    }

    /**
     * 打印一条强调或警告
     * @param content
     */
    public static void STRESS(String content){
        JournalTip journalTip = new JournalTip(content,JournalTipType.STRESS,true);
        JournalSaver.SaveJournal(journalTip);
        //如果开启了线上日志转存功能则不向控制台打印
        if(!JournalConfig.isOpenOnlineSaver){
            System.out.println("\033[34;2m"+journalTip.getTip() + "\033[0m");
        }

    }

    /**
     * 打印一条异常
     * @param content
     */
    public static void EXCEPTION(String content){
        JournalTip journalTip = new JournalTip(content,JournalTipType.EXCEPTION,true);
        JournalSaver.SaveJournal(journalTip);
        //如果开启了线上日志转存功能则不向控制台打印
        if(!JournalConfig.isOpenOnlineSaver){
            System.out.println("\033[33;4m"+journalTip.getTip() + "\033[0m");
        }

    }

    /**
     * 打印一条错误
     * @param content
     */
    public static void WRONG(String content){
        JournalTip journalTip = new JournalTip(content,JournalTipType.WRONG,true);
        JournalSaver.SaveJournal(journalTip);
        if(!JournalConfig.isOpenOnlineSaver){
            System.out.println("\033[31;7m"+journalTip.getTip() + "\033[0m");
        }

    }

    /**
     * 内部类，用于获取打印信息时的系统信息，并可以返回一条格式化的打印数据
     */
     static class JournalTip{

        String content;
        String logTime;
        String className;
        String methodName;
        String lineNumber;
        String typeName;
        JournalTip(String content,JournalTipType type,boolean innerMark){
            StackTraceElement stackTrace;
            if(innerMark){
                stackTrace = Thread.currentThread().getStackTrace()[3];
            }else {
                stackTrace = Thread.currentThread().getStackTrace()[4];
            }

            this.content=content.replace(',',' ');
            this.typeName = type.name();
            this.logTime=LocalDateTime.now().toString().replace('T','-');
            this.className=stackTrace.getClassName();
            this.methodName=stackTrace.getMethodName();
            this.lineNumber=Integer.toString(stackTrace.getLineNumber());
        }

        String getTip(){
           return "[" + this.typeName+ "]" +"\t"+this.content +"\t"+"line:"
                   +this.lineNumber+"\t"+this.className+" :"+this.methodName+"\t"+this.logTime;
        }

    }
}
