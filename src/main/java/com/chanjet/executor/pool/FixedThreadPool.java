package com.chanjet.executor.pool;

import com.chanjet.executor.MyTask;
import com.chanjet.executor.custom.CustomRejectedExecutionHandler;
import com.chanjet.executor.custom.CustomThreadFactory;
import com.chanjet.executor.custom.ThreadPoolMonitor;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/12 23:54
 **/
@Component
public class FixedThreadPool extends ThreadPoolMonitor {

    private ExecutorService newFixed = Executors.newFixedThreadPool(10);

    @Override
    public ThreadPoolExecutor getThreadPoolExecutor() {
        pool = new ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(20),
                new CustomThreadFactory("Sync-Thread-Pool"),
                new CustomRejectedExecutionHandler());
        pool.allowCoreThreadTimeOut(false);
        return pool;
    }

    @Override
    public void test() {
        Long start = System.currentTimeMillis();
        for (int i = 1; i <= 40; i++) {
            pool.execute(new MyTask(i));
        }
        logger.info("用时：" + String.valueOf(System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        FixedThreadPool fixedThreadPool = new FixedThreadPool();
        fixedThreadPool.start();
    }
}