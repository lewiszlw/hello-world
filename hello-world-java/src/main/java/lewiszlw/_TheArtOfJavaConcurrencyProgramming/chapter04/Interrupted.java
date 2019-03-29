package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/17
 * Time:20:48
 */
public class Interrupted {
    public static void main(String[] args) throws Exception {

        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        // 休眠5s，让sleepThread和busyThread充分运行
        TimeUnit.SECONDS.sleep(5);

        busyThread.interrupt();
        sleepThread.interrupt();

        System.out.println("SleepThread interrupt is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupt is " + busyThread.isInterrupted());

        // 防止sleepThread和busyThread立刻退出
        SleepUtils.second(2);
    }

    static class SleepRunner implements Runnable {
        @Override public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override public void run() {
            while (true) {

            }
        }
    }
}
