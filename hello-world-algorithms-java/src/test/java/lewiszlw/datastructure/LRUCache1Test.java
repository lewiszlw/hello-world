package lewiszlw.datastructure;

import lewiszlw.datastructure.lrucache.LRUCache1;
import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-02
 */
public class LRUCache1Test {

    @Test
    public void test() {
        LRUCache1 lruCache1 = new LRUCache1(2);
        lruCache1.put(1, 1);
        lruCache1.put(2, 2);
        lruCache1.printCache();
        Assert.assertTrue("assert1", lruCache1.get(1) == 1);

        lruCache1.put(3, 3);
        Assert.assertTrue("assert2", lruCache1.get(2) == -1);

        lruCache1.put(4, 4);
        Assert.assertTrue("assert3", lruCache1.get(1) == -1);
        Assert.assertTrue("assert4", lruCache1.get(3) == 3);
        Assert.assertTrue("assert5", lruCache1.get(4) == 4);
    }
}
