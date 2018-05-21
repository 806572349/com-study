package stream;


import java.util.Random;
import java.util.stream.Stream;

/**
 * 流的中间操作
 * create by Nemo
 * 2018/5/20  9:20
 */
public class StreamDemo3 {



    public static void main(String[] args) {
        String str="123 sdf sdf";
        //把每个单词的长度调用出来
        Stream.of(str.split(" ")).filter(s->s.length()>2).map(s->s.length()).forEach(System.out::print);
        //flatMap A->B 属性（是个集合）， 最终得到所有的A 元素里面的所有B属性集合
        // intStream long  并不是stream 的子类 要进行宝箱 boxed
        Stream.of(str.split(" ")).flatMap(s->s.chars().boxed()).forEach(x-> System.out.println((char) x.intValue()));


        ///peek 用于debug  是个中间操作  和foreach是终止操作
        Stream.of(str.split(" ")).peek(System.out::print).forEach(System.out::print);
        //limit 使用 ，主要用户无限流
        new Random().ints().filter(x->x>10&&x<100).limit(10).forEach(System.out::print);

    }
}
