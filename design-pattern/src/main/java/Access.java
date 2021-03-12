import config.lincat.journal.Journal;
import config.lincat.journal.JournalConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author 帆哥续写辉煌
 */
public class Access {
    public static void main(String[] args) throws IOException, InterruptedException {
        JournalConfig.setLinCatJournal("C:\\Users\\帆哥续写辉煌\\Desktop\\","testJournal",true,2);
        for(int i=0;i<4;i++){
            Journal.STRESS("This is a stress:"+i);
            try {
                System.out.println("System.out.print test"+i);
                System.out.println(1/0);
            }catch (Exception e){
                e.printStackTrace();
            }
            Thread.sleep(1000*60);
        }

}

}
