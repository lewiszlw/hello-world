package lewiszlw.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Desc: CountDownLatch使用示例
 *
 * @author zhanglinwei02
 * @date 2019-03-30
 */
public class CountDownLatchDemo {

    // static CountDownLatch countDownLatch = new CountDownLatch(3);
    // main线程会一直阻塞

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            System.out.println("t1 count start: " + countDownLatch.getCount());
            countDownLatch.countDown();
            System.out.println("t1 count end: " + countDownLatch.getCount());
        });
        Thread t2 = new Thread(() -> {
            System.out.println("t2 count start: " + countDownLatch.getCount());
            countDownLatch.countDown();
            System.out.println("t2 count end: " + countDownLatch.getCount());
        });
        t1.start();
        t2.start();

        countDownLatch.await();

        // countDownLatch.await(3, TimeUnit.SECONDS);  //最多阻塞3秒

        System.out.println("main end");
    }
}
