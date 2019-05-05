package lewiszlw.lock.spinlock;

import com.google.common.collect.Sets;
import org.junit.Assert;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-30
 */
public class SpinLockV2 {

    // 获取锁超时时间(微秒)
    private static final long LOCK_TIME_OUT_MILLS = 10 * 1000;

    // 尝试获取锁次数每超过设定(PARK_TIMES_BASE)倍数, 则进行锁膨胀
    private static final int PARK_TIMES_BASE = 2;

    // 阻塞时间(纳秒)
    private static final long PARK_TIMES_BASE_NANOS = 1 * 1000;

    private AtomicBoolean spinLock = new AtomicBoolean(false);

    public void lock() {

        // 尝试获取锁次数
        int times = 1;

        long begin = System.currentTimeMillis();
        // 剩余时间
        long rest = LOCK_TIME_OUT_MILLS;

        while (!spinLock.compareAndSet(false, true)) {
            System.out.println("线程" + Thread.currentThread().getName() + "加锁失败，继续重试");

            // 超时机制
            // rest -= (System.currentTimeMillis() - begin);
            rest = LOCK_TIME_OUT_MILLS - (System.currentTimeMillis() - begin);
            if (rest <= 0) {
                System.out.println("begin: " + begin + ", now: " + System.currentTimeMillis()
                        + ", diff(秒): " + (System.currentTimeMillis() - begin) / 1000);
                throw new RuntimeException("线程" + Thread.currentThread().getName() + "获取锁超时");
            }

            // 锁膨胀机制
            // 循环尝试获取锁次数每到达PARK_TIMES_BASE整数倍，则将线程挂起
            if (times % PARK_TIMES_BASE == 0) {
                // 阻塞时间随着次数指数增加
                long backOffTime = PARK_TIMES_BASE_NANOS << (times / PARK_TIMES_BASE);
                long parkTime = backOffTime < rest? backOffTime: rest;
                System.out.println("线程" + Thread.currentThread().getName() + "加锁失败达到"
                        + times + "次，将阻塞" + parkTime + "纳秒");
                LockSupport.parkNanos(parkTime);
            }
            times ++;
        }
    }

    public void unlock() {
        spinLock.compareAndSet(true, false);
    }


    private static int count = 0;
    public static void main(String[] args) {
        SpinLockV2 spinLockV2 = new SpinLockV2();
        Set<Integer> set = Sets.newConcurrentHashSet();
        // 有序输出
        // 锁竞争情况明显下降，不过仍有超时情况
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(() -> {
                spinLockV2.lock();
                count ++;
                Assert.assertTrue(!set.contains(count));
                set.add(count);
                System.out.println("count: " + count);
                spinLockV2.unlock();
            });
            t.start();
        }
        Assert.assertTrue(set.size() == 1000);

    }
}
