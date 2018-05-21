package stream;

import java.util.stream.IntStream;

/**
 * create by Nemo
 * 2018/5/20  9:01
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        int[] nums={1,2,3};
        //stream内部迭代
        //map就是中间操作（返回stream 的操作）
        //sum就是终止操作
        int sum = IntStream.of(nums).map(StreamDemo1::doubleNum).sum();
        System.out.println(sum);

        System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行");
        IntStream intStream = IntStream.of(nums).map(StreamDemo1::doubleNum);

    }

    public static int doubleNum(int i){
        System.out.println("执行了乘以2");
        return i*2;
    }
}
