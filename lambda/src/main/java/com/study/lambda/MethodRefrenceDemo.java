package com.study.lambda;

import java.util.function.*;

/**
 * create by Nemo
 * 2018/5/19  23:03
 */
class Dog{
    private String name="xiaogou ";

    public Dog() {
    }

    /**
     * 带参数的构造函数
     * @param name
     */
    public Dog(String name) {
        this.name = name;
    }

    /**
     * 默认十斤
     */
    private int food=10;
    public static void bark(Dog dog) {
        System.out.println(dog+"加了");
    }

    /**
     * 吃狗粮
     * JDK 默认会把当前实力传入到非静态，参数名为this，位置是第一个
     * @param num
     */
    public  int eat(Dog this,int num) {
        System.out.println("吃了："+num+"斤狗粮");
        this.food-=num;
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
public class MethodRefrenceDemo {
    public static void main(String[] args) {
        Dog dog=new Dog();
        dog.eat(1);
        //方法引用
        Consumer<String> consumer= System.out::println;
        consumer.accept("接受的数据");

        //静态方法的方法引用
        Consumer<Dog>consumer1=Dog::bark;
        consumer1.accept(dog);

        //非静态方法，使用对象实例的方法引用
//        Function<Integer,Integer> function=dog::eat;
//        UnaryOperator<Integer> function=dog::eat;
        IntUnaryOperator function=dog::eat;
        System.out.println("还剩下"+function.applyAsInt(2));

        //使用类名来方法引用
        BiFunction<Dog,Integer,Integer> eatFunction=Dog::eat;
        eatFunction.apply(dog,1);

        //构造函数的方法引用
        Supplier<Dog> supplier=Dog::new;
        System.out.println("创建了新对象"+supplier.get());
        //带参数的构造函数的方法引用 去找输入string 输出dog 的构造方法
        Function<String,Dog> function1=Dog::new;
        System.out.println(function1.apply("旺财"));




    }
}
