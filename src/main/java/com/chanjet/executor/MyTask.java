package com.chanjet.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 可实现Comparable接口对任务排序操作 对PriorityBlockingQueue队列有效
 *
 * @Author Mr.Jimmy
 * @Date 2021/4/12 23:00
 **/
public class MyTask implements Runnable, Comparable<MyTask> {

    private static final Logger logger = LoggerFactory.getLogger(MyTask.class);

    int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {

        logger.info(Thread.currentThread().getName() + "线程开始任务" + i);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(Thread.currentThread().getName() + "线程完成任务" + i);
    }

    @Override
    public int compareTo(MyTask o) {
        return this.i < o.i ? -1 : 1;
    }
}