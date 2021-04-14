package com.chanjet.executor.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/14 23:41
 **/
public abstract class ThreadPoolMonitor {
    public static final Logger logger = LoggerFactory.getLogger("task");
    protected ThreadPoolExecutor pool;
    private static ThreadPoolExecutor work = new ThreadPoolExecutor(
            1,
            1,
            0,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new CustomThreadFactory("Monitor-Thread-Pool"),
            new CustomRejectedExecutionHandler());

    public void monitor() {
        work.execute(() -> {
            while (pool.isTerminating()) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("----------------------------------------------------");
                logger.info("核心线程数:{}", pool.getCorePoolSize());
                logger.info("线程池数:{}", pool.getPoolSize());
                logger.info("队列任务数:{}", pool.getQueue().size());
                logger.info("----------------------------------------------------");
            }
        });
    }

    public void start() {
        monitor();
        test();
    }

    /**
     * 测试
     */
    public abstract void test();

    /**
     * 自定义 线程池
     *
     * @return ThreadPoolExecutor
     */
    public abstract ThreadPoolExecutor getThreadPoolExecutor();
}