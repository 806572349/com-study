package com.study.lambda;

import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * create by Nemo
 * 2018/5/19  22:58
 */
public class FuncitonDemo {
    public static void main(String[] args) {
        //断言接口
        Predicate<Integer> predicate=i->i>0;

        //断言接口
        IntPredicate predicate2= i->i>0;
        System.out.println(predicate.test(1));
        //消费接口
        Consumer<String> consumer=s-> System.out.println(s);
        consumer.accept("111");
        consumer.andThen(x->{
            System.out.println("after");
        }).accept(1+"");

    }
}
