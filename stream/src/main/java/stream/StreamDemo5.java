package stream;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 并行流
 * create by Nemo
 * 2018/5/20  9:48
 */
public class StreamDemo5 {

    public static void main(String[] args) throws InterruptedException {
        ///调用parallel产生并行流
//        IntStream.range(1,100).parallel().peek(StreamDemo5::debug).count();


        //先并行 再串行
        //多次调用parallel  sequential已最后一次调用为准
//        IntStream.range(1,100)
//                .parallel()
//                .peek(StreamDemo5::debug2)
//                //调用 产生串行流
//                .sequential()
//                .count();
        //并行流使用得线程池   ForkJoinPool
        //默认得线程数是当前得cpu的个数
        //使用这个属性可以修改默认的线程数
        /*System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","20");
        IntStream.range(1,100)
                .parallel()
                .peek(StreamDemo5::debug3)
                .count();*/
        // 使用自己的线程池，不使用默认线程池，防止任务被阻塞
        //
        ForkJoinPool forkJoinPool = new ForkJoinPool(20);
        forkJoinPool.submit(()-> IntStream.range(1,100)
                .parallel()
                .peek(StreamDemo5::debug3)
                .count());
        forkJoinPool.shutdown();
        synchronized (forkJoinPool){
            forkJoinPool.wait();
        }


    }
    public static void debug(int i){

        System.out.println("debug"+i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void debug2(int i){

        System.out.println("debug2"+i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void debug3(int i){

        System.out.println(Thread.currentThread().getName()+"debug3"+i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
