package com.chanjet.executor.custom;

import com.chanjet.executor.MyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/9 0:09
 **/
public class CustomThreadPool {
    private static final Logger logger = LoggerFactory.getLogger("task");
    protected static ThreadPoolExecutor pool = getThreadPoolExecutors();

    private static ThreadPoolExecutor getThreadPoolExecutors() {

        pool = new ThreadPoolExecutor(
                2,
                10000,
                120,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new CustomThreadFactory("Custom-Thread-Pool"),
                new CustomRejectedExecutionHandler());
        pool.allowCoreThreadTimeOut(false);
        return pool;
    }

    protected static ThreadPoolExecutor work = new ThreadPoolExecutor(
            1,
            2,
            120,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new CustomRejectedExecutionHandler());

    public static void main(String[] args) {

        Long start = System.currentTimeMillis();
        for (int i = 1; i <= 40; i++) {
            pool.execute(new MyTask(i));
        }
        pool.shutdown();
        logger.info("用时：" + String.valueOf(System.currentTimeMillis() - start));

        work.execute(() -> {

            for (int i = 1; i <= 200; i++) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("------------------------" + i + "----------------------------");
                logger.info("核心线程数" + pool.getCorePoolSize());
                logger.info("线程池数" + pool.getPoolSize());
                logger.info("队列任务数" + pool.getQueue().size());

            }
        });
    }


}