package lewiszlw.javase.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-08
 */
public class ExecutorServiceDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        AtomicInteger page = new AtomicInteger(0);
        Runnable task = () -> {
            while (true) {
                try {
                    long begin = System.currentTimeMillis();
                    int currPage = page.getAndIncrement();
                    Thread.sleep(3000);
                    System.out.println("page: " + currPage + "清洗完成");
                    System.out.println("耗时：" + (System.currentTimeMillis() - begin)/1000 + "s");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        for (int i = 0; i < 6; i++) {
            executorService.submit(task);
        }
    }
}
