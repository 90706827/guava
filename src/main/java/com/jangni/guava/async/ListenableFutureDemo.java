package com.jangni.guava.async;

import com.google.common.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ListenableFutureDemo
 * @Description ListenableFuture 示例
 * @Author Mr.Jangni
 * @Date 2019/4/11 22:28
 * @Version 1.0
 **/
public class ListenableFutureDemo {
    static final Logger logger = LoggerFactory.getLogger(ListenableFutureDemo.class);

    static final ExecutorService executor = Executors.newFixedThreadPool(50);
    // 创建线程池
    static final ListeningExecutorService service = MoreExecutors.listeningDecorator(executor);

    final List<String> value = Collections.synchronizedList(new ArrayList<String>());

    public static void main(String[] args) throws Exception {
        final List<String> value = Collections
                .synchronizedList(new ArrayList<String>());
        try {
            List<ListenableFuture<String>> futures = new ArrayList<ListenableFuture<String>>();
            // 将实现了callable的任务放入到线程池中，得到一个带有回调机制的ListenableFuture实例，
            // 通过Futures.addCallback方法对得到的ListenableFuture实例进行监听，一旦得到结果就进入到onSuccess方法中，
            // 在onSuccess方法中将查询的结果存入到集合中
            for (int i = 0; i < 10; i++) {
                final int index = i;
                if (i == 9) {
                    Thread.sleep(500 * i);
                }
                ListenableFuture<String> sfuture = service
                        .submit(() -> {
                            long time = System.currentTimeMillis();
                            logger.info("Finishing sleeping task{}: {}", index, time);
                            return String.valueOf(time);
                        });

                sfuture.addListener(new Runnable() {
                    @Override
                    public void run() {
                        logger.info("Listener be triggered for task{}.", index);
                    }
                }, service);

                Futures.addCallback(sfuture, new FutureCallback<String>() {
                    public void onSuccess(String result) {
                        logger.info("Add result value into value list {}.", result);
                        value.add(result);
                    }

                    public void onFailure(Throwable t) {
                        logger.info("Add result value into value list error.", t);
                        throw new RuntimeException(t);
                    }
                },service);
                // 将每一次查询得到的ListenableFuture放入到集合中
                futures.add(sfuture);
            }

            // 这里将集合中的若干ListenableFuture形成一个新的ListenableFuture
            // 目的是为了异步阻塞，直到所有的ListenableFuture都得到结果才继续当前线程
            // 这里的时间取的是所有任务中用时最长的一个
            ListenableFuture<List<String>> allAsList = Futures.allAsList(futures);
            allAsList.get();
            logger.info("All sub-task are finished.");
        } catch (Exception ignored) {
            ignored.getMessage();
        }
    }

}
