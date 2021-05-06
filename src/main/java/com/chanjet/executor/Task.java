package com.chanjet.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    private int i;

    public Task(int i) {
        this.i = i;
    }

    @Override
    public void run() {

        logger.info(Thread.currentThread().getName() + "线程完成任务" + i);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}