package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter09;

import java.util.concurrent.*;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/10/31
 * Time:17:11
 */
public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 3, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        Future<?> future = threadPoolExecutor.submit(new Runnable() {
            @Override public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(future.isDone());
        TimeUnit.SECONDS.sleep(5);
        System.out.println(future.isDone());
        threadPoolExecutor.shutdownNow();
    }

}
