package com.chanjet.executor;

import java.util.concurrent.*;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/8 22:29
 **/
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService newCached = Executors.newCachedThreadPool();
        ExecutorService newFixed = Executors.newFixedThreadPool(10);
        ExecutorService newSingle = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            newFixed.execute(new MyTask(i));
        }
    }
}