package com.chanjet.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 *
 *
 * @Author Mr.Jimmy
 * @Date 2021/4/12 23:00
 **/
public class MyTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MyTask.class);

    public int i;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {

        logger.info(Thread.currentThread().getName() + "线程开始任务>" + i);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        logger.info(Thread.currentThread().getName() + "线程完成任务" + i);
    }
}