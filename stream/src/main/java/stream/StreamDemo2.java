package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流的创建
 * create by Nemo
 * 2018/5/20  9:13
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        List<String>list=new ArrayList<>();

        //从集合创建
        list.stream();

        list.parallelStream();

        //从数组中创建
        Arrays.stream(new int[]{2,3,4});

        //创建数字流
        IntStream.of(1,2,3);
        IntStream.rangeClosed(1,10);

        //使用random 创建一个无限流
        new Random().ints().limit(10);

        Random random = new Random();
        //自己产生流
        Stream.generate(()->random.nextInt()).limit(10);

    }
}
