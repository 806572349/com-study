package com.study.lambda;

/**
 * create by Nemo
 * 2018/5/19  22:38
 */
@FunctionalInterface
interface Interface1{
    int doubleNum(int i);

    default  int add(int x,int y){
        return x+y;
    }

    default  int product(int x,int y){
        return x-y;
    }
}

@FunctionalInterface
interface Interface2{
    int doubleNum(int i);



    default  int product(int x,int y){
        return x-y;
    }
}

interface Interface3 extends Interface1,Interface2{


    @Override
    default int doubleNum(int i) {
        return 0;
    }

    @Override
    default int add(int x, int y) {
        return 0;
    }

    @Override
    default int product(int x, int y) {
        return 0;
    }
}
public class LambdaDemo1 {
    public static void main(String[] args) {
        Interface1 i1=(i)->i*2;
        int add = i1.add(1, 2);
        System.out.println("add"+add);

        int a = i1.doubleNum(1);
        System.out.println(a);


        int product = i1.product(1, 2);
        System.out.println(product);
        //这种是最常见的写法
        Interface1 i2=(i)->i*2;

        Interface1 i3=(i)->{
            System.out.println("ok");
            return i*2;

        };
    }
}
