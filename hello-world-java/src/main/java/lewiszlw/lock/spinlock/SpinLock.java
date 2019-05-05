package lewiszlw.lock.spinlock;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-30
 */
@Slf4j
public class SpinLock {

    private AtomicBoolean spinLock = new AtomicBoolean(false);

    public void lock() {

        // 其他线程会不断循环试图获取锁
        // 当出现锁竞争时，不能膨胀为悲观锁等待，也没有过期超时，可能大量占用CPU时间
        while (!spinLock.compareAndSet(false, true)) {
            System.out.println("线程" + Thread.currentThread().getName() + "加锁失败，继续重试");
        }
    }

    public void unlock() {
        spinLock.compareAndSet(true, false);
    }


    private static int count = 0;
    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();
        Set<Integer> set = Sets.newConcurrentHashSet();
        // 有序输出
        // 当改成1000个线程时，锁竞争激烈，消耗时间剧增
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                spinLock.lock();
                count ++;
                Assert.assertTrue(!set.contains(count));
                set.add(count);
                System.out.println("count: " + count);
                spinLock.unlock();
            });
            t.start();
        }
        Assert.assertTrue(set.size() == 100);
    }

}
