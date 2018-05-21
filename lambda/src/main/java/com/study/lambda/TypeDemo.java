package com.study.lambda;

/**
 *
 * 类型推断
 * create by Nemo
 * 2018/5/19  23:28
 */
@FunctionalInterface
interface IMath{
    int add(int x,int y);
}

interface IMath2{
    int add(int x,int y);
}
public class TypeDemo {
    public static void main(String[] args) {
        //变量类型定义
        IMath lambda=(x,y)->x+y;
        //数组
        IMath[] lambdas={((x, y) -> x+y)};
        //通过返回类型
        IMath    ceatelambda=createLambda();
        TypeDemo demo=new TypeDemo();
        //当有二义性的时候，使用强转
        demo.test((IMath) (x, y)->x+y);

    }
    public  void test(IMath iMath){

    }
    public  void test(IMath2 iMath){

    }

    public  static  IMath createLambda(){
        return ((x, y) -> x+y);
    }
}
