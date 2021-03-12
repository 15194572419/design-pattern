package config.lincat.journal;

/**
 * lincat日志配置类
 */
public class JournalConfig {


    /**
     * 日志地址
     */
    static String journalPath = "";

    /**
     * 日志名称
     */
    static String journalName ="linCatJournal";

    /**
     *是否启动线上日志转存功能
     */
    static boolean  isOpenOnlineSaver = false;

    /**
     * 线上转存周期:单位分钟
     */
    static int onlineRedirectCircle = 480;



    /**
     * 开启linCat日志系统：仅设置日志路径
     */
    public static void setLinCatJournal(String journalPath,String journalName){
        JournalConfig.journalPath =journalPath;
        JournalConfig.journalName =journalName;
    }

    /**
     * 开启linCat日志系统：仅设置线上转存参数
     * @param isOpenOnlineSaver
     * @param onlineRedirectCircle
     */
    public static void setLinCatJournal(boolean isOpenOnlineSaver,int onlineRedirectCircle){
        JournalConfig.isOpenOnlineSaver = isOpenOnlineSaver;
        JournalConfig.onlineRedirectCircle = onlineRedirectCircle;

        if(JournalConfig.isOpenOnlineSaver){
            JournalOnlineSaver journalOnlineSaver = new JournalOnlineSaver();
            journalOnlineSaver.openOnlineSave();
        }

    }

    public static void setLinCatJournal(String journalPath,String journalName,boolean isOpenOnlineSaver,int onlineRedirectCircle){
        JournalConfig.journalPath =journalPath;
        JournalConfig.journalName=journalName;
        JournalConfig.isOpenOnlineSaver=isOpenOnlineSaver;
        JournalConfig.onlineRedirectCircle=onlineRedirectCircle;

        if(JournalConfig.isOpenOnlineSaver){
            JournalOnlineSaver journalOnlineSaver = new JournalOnlineSaver();
            journalOnlineSaver.openOnlineSave();
        }
    }
}
