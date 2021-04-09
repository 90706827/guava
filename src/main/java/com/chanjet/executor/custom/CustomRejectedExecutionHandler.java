package com.chanjet.executor.custom;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author Mr.Jimmy
 * @Date 2021/4/9 0:13
 **/
public class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //记录异常
        //报警处理
//        CustomThreadPool.work.execute(r);
        throw new RejectedExecutionException("Task " + r.toString() + " rejected from " + executor.toString());
    }
}