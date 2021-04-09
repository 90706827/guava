package com.chanjet.executor.custom;

import com.chanjet.executor.MyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/9 0:09
 **/
public class CustomThreadPool {
    private static final Logger logger = LoggerFactory.getLogger(CustomThreadPool.class);
    protected static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            4,
            2000,
            30,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new CustomThreadFactory("Custom-Thread-Pool"),
            new CustomRejectedExecutionHandler());
//    protected static ThreadPoolExecutor work = new ThreadPoolExecutor(
//            10,
//            20,
//            30,
//            TimeUnit.SECONDS,
//            new ArrayBlockingQueue<Runnable>(100),
//            new CustomThreadFactory("Custom-Thread-Pool2"),
//            new CustomRejectedExecutionHandler());

    public static void main(String[] args) {


        for (int i = 0; i < 1000000; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                pool.execute(new MyTask(i));
        }

    }


}