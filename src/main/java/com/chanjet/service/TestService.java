package com.chanjet.service;

import com.chanjet.executor.MyTask;
import com.chanjet.executor.custom.CustomRejectedExecutionHandler;
import com.chanjet.executor.custom.CustomThreadFactory;
import com.chanjet.executor.pool.FixedThreadPool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jimmy
 */
@Service
public class TestService {


    public void test(int i){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                8,           //核心线程池
                40,          //最大线程数
                60,          //非核心线程空闲超时时间
                TimeUnit.SECONDS,                       //超时时间单位
                new SynchronousQueue<>(),               //存放任务的阻塞队列
                new CustomThreadFactory("Thread-Pool"), //自定义线程工程
                new CustomRejectedExecutionHandler()    //自定义饱和策略
        );
        pool.allowCoreThreadTimeOut(true); //可直接让空闲时间生效
    }
}
