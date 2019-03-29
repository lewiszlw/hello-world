package lewiszlw.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Desc:
 * RateLimiter使用的是一种叫令牌桶的流控算法，RateLimiter会按照一定的频率往桶里扔令牌，
 * 线程拿到令牌才能执行，比如你希望自己的应用程序QPS不要超过1000，那么RateLimiter设置
 * 1000的速率后，就会每秒往桶里扔1000个令牌
 *
 * @author zhanglinwei02
 * @date 2019-03-01
 */
public class RateLimiterDemo {

    /**
     * 每秒限制量
     */
    final static RateLimiter rateLimiter = RateLimiter.create(2.0);

    final static Executor executor = Executors.newFixedThreadPool(5);

    final static int COUNT = 100;

    public static void submit(Runnable task, Executor executor) {
        rateLimiter.acquire();
        executor.execute(task);
    }

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            submit(() -> System.out.println(System.currentTimeMillis()), executor);
        }
    }

}
