package com.chanjet.executor.custom;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/9 0:09
 **/
public class CustomThreadFactory implements ThreadFactory {
    private AtomicInteger count = new AtomicInteger(0);

    private final String name;

    public CustomThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        String threadName = name + "-" + count.addAndGet(1);
        System.out.println(threadName);
        t.setName(threadName);
        return t;
    }
}