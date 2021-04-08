package com.chanjet.executor.custom;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/9 0:09
 **/
public class CustomThreadPool {

    ThreadPoolExecutor pool = new ThreadPoolExecutor(
            10,
            30,
            30,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue<Runnable>(10),
            new CustomThreadFactory("Custom Thread Pool"),
            new CustomRejectedExecutionHandler());

}