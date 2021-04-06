package com.chanjet.guava;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Mr.Jimmy
 * @Date 2021/3/30 22:27
 * @Description 线程池
 **/
public class BaseService {
    protected ThreadPoolTaskExecutor pool = getThreadPoolTaskExecutor();
    protected static ThreadPoolExecutor pool2 = getThreadPoolExecutors();
    protected static ListeningExecutorService service = MoreExecutors.listeningDecorator(pool2);

    private ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        pool = new ThreadPoolTaskExecutor();
        //核心线程数
        pool.setCorePoolSize(1);
        //线程最大值
        pool.setMaxPoolSize(1);
        pool.setKeepAliveSeconds(60);
        pool.setAllowCoreThreadTimeOut(true);
        pool.setThreadNamePrefix("task-");
        pool.afterPropertiesSet();
        return pool;
    }

    private static ThreadPoolExecutor getThreadPoolExecutors() {
        pool2 = new ThreadPoolExecutor(20, 100, 120, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        return pool2;
    }
}