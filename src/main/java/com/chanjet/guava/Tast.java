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


    public List<String> exce() {
        List<String> result = new ArrayList<>();

        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(pool2.submit(() -> {
                Thread.sleep(1000);
                return "a";
            }));
        }

        for (Future<String> rest : list) {
            try {
                result.addAll(Collections.singleton(rest.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        Tast tast = new Tast();
        List<String> result = tast.exce();
        System.out.println(result.size());
        System.out.println(System.currentTimeMillis() - start);
    }

}