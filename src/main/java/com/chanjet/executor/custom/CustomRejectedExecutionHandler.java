package com.chanjet.executor.custom;

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

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //记录异常 报警处理
        logger.error("Task " + r.toString() + " rejected from " + executor.toString());
        logger.error("----------------------------------------------------");
        logger.error("核心线程数:{}", executor.getCorePoolSize());
        logger.error("线程池数:{}", executor.getPoolSize());
        logger.error("队列任务数:{}", executor.getQueue().size());
        logger.error("----------------------------------------------------");
    }

    public void AbortPolicy(){

    }

}