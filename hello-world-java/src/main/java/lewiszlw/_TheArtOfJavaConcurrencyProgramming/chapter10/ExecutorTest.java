package lewiszlw._TheArtOfJavaConcurrencyProgramming.chapter10;

import java.util.concurrent.*;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/10/31
 * Time:19:09
 *
 */
public class ExecutorTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        Future<String> future = poolExecutor.submit(new Callable<String>() {
            @Override public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "done";
            }
        });
        System.out.println(future.get());

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
        scheduledThreadPoolExecutor.schedule(new Runnable() {
            @Override public void run() {
                System.out.println("delay");
            }
        }, 5, TimeUnit.SECONDS);
    }
}
