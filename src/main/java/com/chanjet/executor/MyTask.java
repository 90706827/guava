package com.chanjet.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/8 22:32
 **/
public class MyTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MyTask.class);

    int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        logger.info(Thread.currentThread().getName() + "线程做任务" + i);

        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}