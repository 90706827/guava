package com.chanjet.executor;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 一、线程池优点
 * 1、降低开销 - 频繁创建和销毁线程
 * 2、复用和管理 - 线程池复用、线程名称管理
 *
 * @author Mr.Jimmy
 * @date 2021/4/3 16:34
 **/
public class ThreadPool<T> {

    /**
     * 默认策略，阻塞队列满，则丢任务、抛出异常
     */
    RejectedExecutionHandler abortPolicy = new ThreadPoolExecutor.AbortPolicy();

    /**
     * 阻塞队列满，则丢任务，不抛异常
     */
    RejectedExecutionHandler discardPolicy = new ThreadPoolExecutor.DiscardPolicy();

    /**
     * 删除队列中最旧的任务（最早进入队列的任务），尝试重新提交新的任务
     */
    RejectedExecutionHandler discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();

    /**
     * 队列满，不丢任务，不抛异常，若添加到线程池失败，那么主线程会自己去执行该任务
     */
    RejectedExecutionHandler callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

    /**
     *
     */
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20,
            100,
            120,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new ThreadFactoryBuilder().setNameFormat("base_service-pool-%d").build(),
            abortPolicy);

    /**
     * @param runnable
     */
    public void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    public Future<T> submit(Callable<T> callable) {
        return threadPoolExecutor.submit(callable);
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
    }

    public void shutdownNow() {
        threadPoolExecutor.shutdownNow();
    }


}