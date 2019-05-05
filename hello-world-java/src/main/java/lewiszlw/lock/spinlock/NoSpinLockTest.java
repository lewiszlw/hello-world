package lewiszlw.lock.spinlock;

import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-30
 */
public class NoSpinLockTest {

    private static int count = 0;

    @Test
    public void testSpinLock1() {
        // 不加锁，会输出count相同的值或count不有序，子线程会出现断言异常
        Set<Integer> set = Sets.newConcurrentHashSet();
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(() -> {
                count ++;
                Assert.assertTrue(!set.contains(count));
                set.add(count);
                System.out.println("count: " + count);
            });
            t.start();
        }
    }
}
