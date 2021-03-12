package config.lincat.util.threadpool;

import java.util.concurrent.*;

/**
 * @name LinCat线程池
 * @description 由于阿里开发不建议直接使用java内置的线程池，以避免资源耗尽问题。在此使用单例模式，封装线程池。
 * @author 帆哥续写辉煌
 */
public class LCThreadPool {
    /**
     * 单例线程池
     */
    private static ExecutorService threadPool;

    /**
     * 空构造函数，必写，因为默认构造函数的访问限制符是public。在此初始化threadPool实例
     */
    private LCThreadPool(){
        threadPool = new ThreadPoolExecutor(5,
                100,
                1L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024),
                new ThreadFactoryBuilder(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 获取线程池：这里为保证线程安全采用加锁形式，开销较大。
     * @return
     */
    public static synchronized ExecutorService getThreadPool(){
        if(threadPool == null){
           new LCThreadPool();
        }
        return  threadPool;
    }

    /**
     * 实现ThreadFactory接口的内部嵌套类
     */
    private static class ThreadFactoryBuilder implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    }

}
