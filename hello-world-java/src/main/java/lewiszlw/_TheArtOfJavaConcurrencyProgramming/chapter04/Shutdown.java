package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter04;

import java.util.concurrent.TimeUnit;

/**
 * Desc: 终止线程
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/9/19
 * Time:20:28
 */
public class Shutdown {

    public static void main(String[] args) throws Exception {

        Thread countThread = new Thread(new Runner(), "CountThread");
        countThread.start();

        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();

        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }

    private static class Runner implements Runnable {

        private long i;
        /** 静态内部类的属性，如同静态属性，所以需要volatile修饰 */
        private volatile boolean on = true;

        @Override public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel() {
            on = false;
        }
    }
}
