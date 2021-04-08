package com.chanjet.guava;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @Author Mr.Jimmy
 * @Date 2021/3/30 22:27
 * @Description 线程池
 **/
public class BaseService {

    protected static final Logger logger = LoggerFactory.getLogger("base");

    /**
     * 默认策略，阻塞队列满，则丢任务、抛出异常
     */
    private RejectedExecutionHandler abortPolicy = new ThreadPoolExecutor.AbortPolicy();

    /**
     * 阻塞队列满，则丢任务，不抛异常
     */
    private RejectedExecutionHandler discardPolicy = new ThreadPoolExecutor.DiscardPolicy();

    /**
     * 删除队列中最旧的任务（最早进入队列的任务），尝试重新提交新的任务
     */
    private RejectedExecutionHandler discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();

    /**
     * 队列满，不丢任务，不抛异常，若添加到线程池失败，那么主线程会自己去执行该任务
     */
    private RejectedExecutionHandler callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();

    protected ThreadPoolTaskExecutor threadPoolTaskExecutor = getThreadPoolTaskExecutor();
    protected static ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutors();
    protected static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    /**
     * Spring 封装的 java ThreadPoolExecutor 类
     *
     * @return ThreadPoolTaskExecutor
     */
    private ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(20);
        //线程最大值
        threadPoolTaskExecutor.setMaxPoolSize(1000);
        //空闲的线程多久后被销毁
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        //核心线程在规定时间内会被回收
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        //线程池中线程名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix("task-");
        //线程池初始化
        threadPoolTaskExecutor.afterPropertiesSet();
        threadPoolTaskExecutor.setQueueCapacity(0);
        return threadPoolTaskExecutor;
    }

    private static ThreadPoolExecutor getThreadPoolExecutors() {
        threadPoolExecutor = new ThreadPoolExecutor(20, 1000,
                120, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new ThreadFactoryBuilder().setNameFormat("base_service-pool-%d").build());
        return threadPoolExecutor;
    }
}