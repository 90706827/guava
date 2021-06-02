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

    @Override
    public ThreadPoolExecutor getThreadPoolExecutor() {
        pool = new ThreadPoolExecutor(
                10,
                20,
                5,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new CustomThreadFactory("Sync-Thread-Pool"),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        pool.allowCoreThreadTimeOut(true);
        return pool;
    }

    @Override
    public void test() {
        Long start = System.currentTimeMillis();
        for (int i = 1; i <= 40; i++) {
            try{
                pool.execute(new MyTask(i));
            }catch (RejectedExecutionException e){
                e.printStackTrace();
            }
        }
        logger.info("用时：" + String.valueOf(System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        CachedThreadPool cachedThreadPool = new CachedThreadPool();
        cachedThreadPool.start();
    }
}