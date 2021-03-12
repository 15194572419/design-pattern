package config.lincat.journal;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * 日志保存类：用于将一条信息保存在本地日志中
 */
class JournalSaver {

    /**
     * 静态默认方法：只能在本包下使用，将日志消息保存于本地日志中。
     * @return boolean：是否保存成功
     */
    static synchronized boolean  SaveJournal(Journal.JournalTip journalTip){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(JournalConfig.journalPath+JournalConfig.journalName+".csv",true));
            writer.append(journalTip.typeName+','+journalTip.content+","
                    +journalTip.lineNumber+','+journalTip.className+','+journalTip.methodName
            +','+journalTip.logTime);
            writer.newLine();
            writer.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
