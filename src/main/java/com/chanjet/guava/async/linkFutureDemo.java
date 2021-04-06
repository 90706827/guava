package com.chanjet.guava.async;

import com.google.common.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @ClassName linkFutureDemo
 * @Description 链式回调
 * @Author Mr.Jangni
 * @Date 2019/4/14 21:03
 * @Version 1.0
 **/
public class linkFutureDemo {
    static final ExecutorService executor = Executors.newFixedThreadPool(50);
    static final Logger logger = LoggerFactory.getLogger(ListenableFutureDemo.class);
    // 创建线程池
    static final ListeningExecutorService service = MoreExecutors.listeningDecorator(executor);


    public static void main(String[] args) {

        ListenableFuture<String> start = service.submit(() -> {
            logger.info("任务执行开始...");
            return "start";
        });

        ListenableFuture<String> one = Futures.transformAsync(start, s -> service.submit(() -> {
            logger.info(s);
            return "one";
        }), service);


        ListenableFuture<String> two = Futures.transformAsync(one, s -> service.submit(() -> {
            logger.info(s);
            return "two";
        }), service);

        ListenableFuture<String> three = Futures.transformAsync(two, s -> service.submit(() -> {
            logger.info(s);
            return "three";
        }), service);

        ListenableFuture<String> four = Futures.transformAsync(three, s -> service.submit(() -> {
            logger.info(s);
            return "four";
        }), service);

        Futures.addCallback(four, new FutureCallback<String>() {
            public void onSuccess(String result) {
                logger.info(result);
                logger.info("任务执行完成");
            }

            public void onFailure(Throwable t) {

                logger.error("异常",t);
            }
        }, service);
    }
}