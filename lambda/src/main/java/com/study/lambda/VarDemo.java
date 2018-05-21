package com.study.lambda;

import java.util.function.Consumer;

/**
 * 变量引用
 * create by Nemo
 * 2018/5/19  23:36
 */
public class VarDemo {
    public static void main(String[] args) {
        String str="我们不一样";
        Consumer<String> consumer=s-> System.out.println(s+str);
        consumer.accept("222");
    }
}
