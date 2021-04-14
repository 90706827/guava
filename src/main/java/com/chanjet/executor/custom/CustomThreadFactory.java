package com.chanjet.executor.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1、设置线程名称；
 * 2、设置线程优先级
 * 3、线程排序
 *
 * @Author Mr.Jimmy
 * @Date 2021/4/9 0:09
 **/
public class CustomThreadFactory implements ThreadFactory {
    private static final Logger logger = LoggerFactory.getLogger("task");
    private AtomicInteger count = new AtomicInteger(0);

    private final String name;

    public CustomThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        String threadName = name + "-" + count.addAndGet(1);
        logger.info("创建线程：" + threadName);
        t.setName(threadName);
        return t;
    }
}