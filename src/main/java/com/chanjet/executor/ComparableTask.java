package com.chanjet.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jimmy
 * 可实现Comparable接口对任务排序操作 对PriorityBlockingQueue队列有效
 */
public class ComparableTask implements Runnable, Comparable<ComparableTask> {

    private static final Logger logger = LoggerFactory.getLogger(ComparableTask.class);

    int i = 0;

    public ComparableTask(int i) {
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
    }

    @Override
    public int compareTo(ComparableTask o) {
        return this.i < o.i ? -1 : 1;
    }
}