package com.chanjet.executor.pool;

import com.chanjet.executor.MyTask;
import com.chanjet.executor.custom.CustomRejectedExecutionHandler;
import com.chanjet.executor.custom.CustomThreadFactory;
import com.chanjet.executor.custom.ThreadPoolMonitor;

import java.util.concurrent.*;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/14 19:18
 **/
public class SingleThreadPool extends ThreadPoolMonitor {

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public SingleThreadPool() {
        super.pool = getThreadPoolExecutor();
    }

    @Override
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return new ThreadPoolExecutor(1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<>(),
                new CustomThreadFactory("Single-Thread-Pool"),
                new CustomRejectedExecutionHandler());
    }

    @Override
    public void test() {
        Long start = System.currentTimeMillis();
        for (int i = 1; i <= 40; i++) {
            pool.execute(new MyTask(i));
        }
        pool.shutdown();
        logger.info("用时：" + String.valueOf(System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        SingleThreadPool singleThreadPool = new SingleThreadPool();
        singleThreadPool.start();
    }

}