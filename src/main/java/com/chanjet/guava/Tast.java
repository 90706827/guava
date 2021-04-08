package com.chanjet.guava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @Author Mr.Jimmy
 * @Date 2021/3/31 22:35
 * @Description
 **/
public class Tast extends BaseService {
    int sum = 1000;

    public List<String> excec() {
        List<String> resultc = new ArrayList<>();

        List<Future<String>> c = new ArrayList<>();

        for (int i = 0; i < sum; i++) {
            c.add(service.submit(() -> {
                Thread.sleep(1000);
                return "c";
            }));
        }

        for (Future<String> rest : c) {
            try {
                resultc.addAll(Collections.singleton(rest.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return resultc;
    }

    public List<String> excea() {
        List<String> resulta = new ArrayList<>();

        List<Future<String>> a = new ArrayList<>();

        for (int i = 0; i < sum; i++) {
            a.add(threadPoolTaskExecutor.submit(() -> {
                Thread.sleep(1000);
                return "a";
            }));
        }

        for (Future<String> rest : a) {
            try {
                resulta.addAll(Collections.singleton(rest.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resulta;
    }

    public List<String> exceb() {
        List<String> resultb = new ArrayList<>();

        List<Future<String>> b = new ArrayList<>();

        for (int i = 0; i < sum; i++) {
            b.add(threadPoolExecutor.submit(() -> {
                Thread.sleep(1000);
                return "b";
            }));
        }

        for (Future<String> rest : b) {
            try {
                resultb.addAll(Collections.singleton(rest.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultb;
    }

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        Tast tast = new Tast();
        List<String> result = tast.excea();
        logger.info("{}",result.size());
        logger.info("threadPoolTaskExecutor:{}",System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        result = tast.exceb();
        logger.info("{}",result.size());
        logger.info("threadPoolExecutor:{}",System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        result = tast.excec();
        logger.info("{}",result.size());
        logger.info("service:{}",System.currentTimeMillis() - start);
    }

}