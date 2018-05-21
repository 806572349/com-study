package com.study.lambda;

import java.util.stream.IntStream;

/**
 * create by Nemo
 * 2018/5/19  22:31
 */
public class Lambda {
    public static void main(String[] args) {
        int i = IntStream.of(Integer.MAX_VALUE).parallel().min().getAsInt();
        System.out.println(i);


    }
}
