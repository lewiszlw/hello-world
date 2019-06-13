package lewiszlw.problems;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Desc: 俩线程分别持续打印奇数和偶数，实现俩线程的交替打印（从小到大）
 *
 * @author zhanglinwei02
 * @date 2019-03-28
 */
public class TwoThreadPrintOddEvenNumber {

    static final int COUNT = 100;

    /**
     * 实现方式1
     * 利用wait，notify机制
     * 最终会有一个线程一直处于wait状态
     */
    static volatile Integer num = 0;
    public void solution1() {
        System.out.println("================solution1===============");

        // 利用对象的锁机制
        // 调用对象wait()方法，线程会让出锁，自己进入等待状态，同时加入锁对象的等待队列。
        Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num < COUNT) {
                    synchronized (lock) {
                        if (num % 2 == 0) {
                            System.out.println(num++);
                        } else {
                            try {
                                lock.notifyAll();
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num < COUNT) {
                    synchronized (lock) {
                        if (num % 2 == 1) {
                            System.out.println(num++);
                        } else {
                            try {
                                lock.notifyAll();
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }


    /**
     * 实现方式2
     * 线程死循环，时间片到了会切换到其他线程
     */
    static AtomicInteger num2 = new AtomicInteger(0);
    static volatile boolean flag = false;
    public void solution2() throws InterruptedException {
        System.out.println("================solution2===============");
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t1 = new Thread(() -> {
            while (num2.intValue() < COUNT) {
                if (flag) {
                    System.out.println(num2.getAndIncrement());
                    flag = false;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (num2.intValue() < COUNT) {
                if (!flag) {
                    System.out.println(num2.getAndIncrement());
                    flag = true;
                }
            }
        });
        t1.start();
        t2.start();
    }


    public static void main(String[] args) throws InterruptedException {
        //new TwoThreadPrintOddEvenNumber().solution1();
        new TwoThreadPrintOddEvenNumber().solution2();
    }
}
