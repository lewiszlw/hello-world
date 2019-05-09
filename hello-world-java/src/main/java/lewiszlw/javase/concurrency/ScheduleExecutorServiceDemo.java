package lewiszlw.javase.concurrency;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-08
 */
public class ScheduleExecutorServiceDemo {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5);
        AtomicInteger page = new AtomicInteger(0);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("page: " + page.getAndIncrement());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int queueSize = ((ScheduledThreadPoolExecutor) scheduledExecutorService).getQueue().size();
            System.out.println("队列大小: " + queueSize);
        }, 0, 1, TimeUnit.SECONDS);

//        while (true) {
//            int queueSize = ((ScheduledThreadPoolExecutor) scheduledExecutorService).getQueue().size();
//            System.out.println("队列大小: " + queueSize);
//            if (page.get() > 200) {
//                break;
//            }
//            Thread.sleep(1000);
//        }
    }
}
