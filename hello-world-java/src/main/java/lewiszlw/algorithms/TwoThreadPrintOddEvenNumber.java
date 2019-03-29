package lewiszlw.algorithms;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Desc: 俩线程分别持续打印奇数和偶数，实现俩线程的交替打印（从小到大）
 *
 * @author zhanglinwei02
 * @date 2019-03-28
 */
public class TwoThreadPrintOddEvenNumber {

//    static AtomicInteger num = new AtomicInteger(0);
    static volatile Integer num = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (num) {
                    if (num.intValue() % 2 == 1) {
                        System.out.println(num++);
                    }
                }
                try {
                    num.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                while (num.intValue() % 2 == 1) {
//                    System.out.println(num.getAndIncrement());
//                    Thread.yield();
//                    notifyAll();
//                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (num) {
                    if (num.intValue() % 2 == 0) {
                        System.out.println(num++);
                    }
                }
                try {
                    num.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                while (num.intValue() % 2 == 0) {
//                    System.out.println(num.getAndIncrement());
//                    Thread.yield();
//                    notifyAll();
//                }
            }
        });

        t1.start();
        t2.start();
    }
}
