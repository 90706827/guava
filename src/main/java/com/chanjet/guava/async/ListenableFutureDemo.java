package com.chanjet.guava.async;

import com.chanjet.executor.custom.CustomRejectedExecutionHandler;
import com.chanjet.executor.custom.CustomThreadFactory;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ListenableFutureDemo
 * @Description ListenableFuture 示例
 * @Author Mr.Jangni
 * @Date 2019/4/11 22:28
 * @Version 1.0
 **/
public class ListenableFutureDemo {
    static final Logger logger = LoggerFactory.getLogger(ListenableFutureDemo.class);
    static final ExecutorService work = new ThreadPoolExecutor(
            10,
            30,
            30,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new CustomThreadFactory("ListeningExecutorWork-Thread-Pool"),
            new CustomRejectedExecutionHandler());

    static final ExecutorService executor = new ThreadPoolExecutor(
            10,
            30,
            30,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            new CustomThreadFactory("ListeningExecutorService-Thread-Pool"),
            new CustomRejectedExecutionHandler());
    // 创建线程池
    static final ListeningExecutorService service = MoreExecutors.listeningDecorator(executor);


    public static void main(String[] args) throws Exception {

    }

}
