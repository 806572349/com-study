package study;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * create by Nemo
 * 2018/5/20  12:15
 */
public class FlowDemo {
    public static void main(String[] args) throws InterruptedException {
        //1 定位发布者，发布的数据类型是Integer;
        //直接使用jdk 自带的SubmissionPublisher ,它实现Publisher 接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<Integer>();

        //2定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<>(){
            private  Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                //保存订阅关系，需要用他来给发布者响应
                this.subscription=subscription;
                //请求数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                //接收到一个数据 处理
                System.out.println("接受到数据："+item);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //处理完调用request再请求一个数据
                this.subscription.request(1);


                //或者已 不在接受
//                this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                //发生异常
                throwable.printStackTrace();
                // 我们可以告诉发布者，后面不再接受数据了
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("处理完成");
            }
        };

        //3.发布者和订阅者 建立订阅关系
        publisher.subscribe(subscriber);

        //4 生产数据，并发布
        //这里忽略数据生产过程
        //有个 256长度 的array
        //submit 是个block 方法
        int data=111;
        for (int i = 0; i < 1000; i++) {
            System.out.println("生产数据："+i);
            publisher.submit(i);
        }
        publisher.submit(data);
        publisher.submit(data);
        publisher.submit(data);
        //5 结束后关闭发布者
        // 正式环境应该放fianll 或者使用try-resouce 确保关闭
        publisher.close();

        Thread.currentThread().join(1000);


    }
}
