package lewiszlw.javase.timer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-28
 */
public class TimerDemo extends TimerTask {
    @Override
    public void run() {
        System.out.println("TimerTask execute at " + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
                new ThreadFactoryBuilder().setDaemon(true).setNameFormat("dcc-schedule-pool-%d").build());
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("ScheduledExecutorService execute at " + System.currentTimeMillis());
        }, 1, 2, TimeUnit.SECONDS);

        Timer timer = new Timer();
        timer.schedule(new TimerDemo(), 1, 2000);
    }
}
