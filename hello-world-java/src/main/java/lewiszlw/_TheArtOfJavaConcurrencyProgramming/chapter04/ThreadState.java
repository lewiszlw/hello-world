package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/1
 * Time:13:33
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        // 使用两个Blocked线程，一个获取锁成功，一个被阻塞
        new Thread(new Blocked(), "BlockedThread").start();
        new Thread(new Blocked(), "BlockedThread").start();

    }

    /**
     * 该线程不断睡眠
     */
    static class TimeWaiting implements Runnable {
        @Override public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }

    /**
     * 该线程在Waiting.class实例上等待
     */
    static class Waiting implements Runnable {
        @Override public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 该线程在Blocked.class实例上加锁后不会释放该锁
     */
    static class Blocked implements Runnable {
        @Override public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }
}

class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
