package com.study.lambda;

/**
 * create by Nemo
 * 2018/5/19  22:33
 */
public class ThreadDemo {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ok");
            }
        }).start();

       Runnable runnable1= (()-> System.out.println("ok"));
        Runnable runnable2= (()-> System.out.println("ok"));
        //jdk8
        new Thread(runnable1).start();
        System.out.println(runnable1==runnable2);
    }
}
