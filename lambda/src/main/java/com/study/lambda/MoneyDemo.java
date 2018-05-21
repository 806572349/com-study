package com.study.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * create by Nemo
 * 2018/5/19  22:51
 */
//interface  IMoneyFormat{
//    String fromat(int i);
//}
class MyMoney{
    private final int money;

    public MyMoney(int money){
        this.money=money;
    }
    public  void printMoney(Function<Integer,String> moneyFormat){
        System.out.println("存款"+moneyFormat.apply(this.money));
    }
}
public class MoneyDemo {
    public static void main(String[] args) {
        MyMoney me=new MyMoney(100000);
        me.printMoney(i->new DecimalFormat("#.####").format(i));
        Function<Integer,String> moneyFormat=i->new DecimalFormat("#.####").format(i);
        me.printMoney(moneyFormat);

    }
}
