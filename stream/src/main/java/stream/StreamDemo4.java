package stream;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的终止操作
 * create by Nemo
 * 2018/5/20  9:32
 */
public class StreamDemo4 {

    public static void main(String[] args) {

        String str="123 sdf sdf";
        //并行流
        str.chars().parallel().forEach(i-> System.out.println((char) i));
        System.out.println();
        // forEachOrdered保证顺序
        str.chars().parallel().forEachOrdered(i-> System.out.println((char) i));

        //
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        System.out.println(list);

        //使用reduce 拼接字符串
        Optional<String> reduce = Stream.of(str.split(" "))
                .reduce((s1, s2) -> s1 + "|" + s2);

        System.out.println(reduce.orElse(""));

        //带初始化值得reduce
        String s = Stream.of(str.split(" "))
                .reduce("", (s1, s2) -> s1 + "|" + s2);

        Integer integer = Stream.of(str.split(" ")).map(s3 -> s3.length())
                .reduce(0, (s1, s2) -> s1 + s2);
        System.out.println(integer);

        //max 得使用
        Optional<String> max = Stream.of(str.split(" ")).max((s1, s2) -> s1.length() - s2.length());
        System.out.println(max.get());

        OptionalInt first = new Random().ints().findFirst();
        System.out.println(first.getAsInt());
    }
}
