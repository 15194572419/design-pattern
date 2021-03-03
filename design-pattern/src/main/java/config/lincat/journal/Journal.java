package config.lincat.juornal;

import java.time.LocalDateTime;

/**
 * @name linCat日志类
 * @author 帆哥续写辉煌
 * @description 注意保证线程安全
 */
public class Journal {

    private static LocalDateTime localDateTime =LocalDateTime.now();




    public static void LOG(String content){
        System.out.println(localDateTime);
        System.out.println(content);
        String className = Thread.currentThread().getStackTrace()[2].getClassName();//调用的类名
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();//调用的方法名
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();//调用的行数
        System.out.println(className);
        System.out.println(methodName);
        System.out.println(lineNumber);
    }

    public static void LOG(){
        LOG("");
    }

    private class J
}
