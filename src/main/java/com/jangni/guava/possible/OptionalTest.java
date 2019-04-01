package com.jangni.guava.possible;


import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @ClassName OptionalTest
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/4/1 19:26
 * @Version 1.0
 **/
public class OptionalTest {

    private static final Logger logger = LoggerFactory.getLogger(OptionalTest.class);

    public static void main(String[] args) {
        Optional possible = Optional.of("1");
        Optional possibleNull = Optional.of("");
        Set<String> set = new LinkedHashSet<>();
        set.add("str");

        logger.info("possible.isPresent() 如果Optional包含非null的引用（引用存在），返回true：{}", possible.isPresent());
        logger.info("possible.get() 返回Optional所包含的引用，若引用缺失，则抛出 java.lang.IllegalStateException：{}", possible.get());
        try {
            Optional.of(null).get();
        } catch (Throwable t) {
            logger.error(" Optional.of() 创建指定引用的Optional实例，若引用为null则快速失败：", t);
        }
        logger.info("Optional.absent() 创建引用缺失的Optional实例:{}", Optional.absent());
        logger.info("Optional.fromNullable(T) 创建引用缺失的Optional实例:{}", Optional.fromNullable(possible).isPresent());
        logger.info("optional.or(T) 返回Optional所包含的引用，若引用缺失，返回指定的值:{}", possibleNull.or(1));
        logger.info("optional.orNull() 返回Optional所包含的引用，若引用缺失，返回null:{}", possibleNull.orNull());
        logger.info("Set<T> asSet() 返回Optional所包含引用的单例不可变集，如果引用存在，返回一个只有单一元 素的集合，如果引用缺失，返回一个空集合:{}", Optional.of(set).asSet());
    }
}