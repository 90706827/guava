package com.chanjet.executor.custom;

import com.chanjet.executor.MyTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/9 0:13
 **/
public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
    private static final Logger logger = LoggerFactory.getLogger("task");

    CustomRejectedExecutionHandler() {
        super();
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        MyTask task = (MyTask) r;

        String msg = String.format("Thread pool Wain Info:" +
                        " Task [%d], Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d)," +
                        " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)",
                task.i, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(), e.getMaximumPoolSize(), e.getLargestPoolSize(),
                e.getTaskCount(), e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating());
        logger.warn(msg);
    }

}