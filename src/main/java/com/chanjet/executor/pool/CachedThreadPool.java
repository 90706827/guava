package com.chanjet.executor.pool;

import com.chanjet.executor.MyTask;
import com.chanjet.executor.custom.CustomRejectedExecutionHandler;
import com.chanjet.executor.custom.CustomThreadFactory;
import com.chanjet.executor.custom.ThreadPoolMonitor;

import java.util.concurrent.*;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/12 23:28
 **/
public class CachedThreadPool extends ThreadPoolMonitor {

    private ExecutorService newCached = Executors.newCachedThreadPool();

    public CachedThreadPool() {
        pool = getThreadPoolExecutor();
    }

    @Override
    public ThreadPoolExecutor getThreadPoolExecutor() {
        pool = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new CustomThreadFactory("Sync-Thread-Pool"),
                new CustomRejectedExecutionHandler());
        pool.allowCoreThreadTimeOut(false);
        return pool;
    }

    @Override
    public void test() {
        Long start = System.currentTimeMillis();
        for (int i = 1; i <= 40; i++) {
            pool.execute(new MyTask(i));
        }
        pool.shutdown();
        logger.info("用时：" + String.valueOf(System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        CachedThreadPool cachedThreadPool = new CachedThreadPool();
        cachedThreadPool.start();
    }
}