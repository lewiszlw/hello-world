package lewiszlw.datastructure;

import lewiszlw.datastructure.lrucache.LRUCache2;
import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-04
 */
public class LRUCache2Test {

    @Test
    public void test() {
        LRUCache2<String, Integer> lruCache = new LRUCache2<>(3);
        // a
        lruCache.put("a", 1);
        // b a
        lruCache.put("b", 1);
        // a b
        lruCache.put("a", 2);
        // a b
        Assert.assertTrue(lruCache.get("a") == 2);
        // b a
        Assert.assertTrue(lruCache.get("b") == 1);
        Assert.assertTrue(lruCache.size() == 2);

        // c b a
        lruCache.put("c", 3);
        // d c b
        lruCache.put("d", 4);
        Assert.assertTrue(lruCache.size() == 3);
        Assert.assertTrue(lruCache.get("a") == null);
        // c d b
        Assert.assertTrue(lruCache.get("c") == 3);
    }
}
