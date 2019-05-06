package lewiszlw.lock.spinlock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-06
 */
public class ReentrantSpinLock {

    private AtomicReference<Thread> spinLock = new AtomicReference<>();

    // 重入计数器
    private AtomicInteger count = new AtomicInteger(0);

    public void lock() {
        if (spinLock.get() == Thread.currentThread()) {
            // 当前线程已获取到锁
            // 重入, count ++
            count.incrementAndGet();
            return;
        }
        while (!spinLock.compareAndSet(null, Thread.currentThread())) {
            // 自旋获取锁
        }
    }

    public void unlock() {
        if (spinLock.get() == Thread.currentThread()) {
            if (count.intValue() > 0) {
                // 重入, count --
                count.decrementAndGet();
            } else {
                // count == 0 即没有重入
                spinLock.compareAndSet(Thread.currentThread(), null);
            }
        }
    }
}
