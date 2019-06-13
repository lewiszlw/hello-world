package lewiszlw;

import lewiszlw.algorithms.BloomFilter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-03-30
 */
public class BloomFilterTest {

    @Test
    public void test1() {
        BloomFilter bloomFilter = new BloomFilter(1 << 15);
        Integer i1 = Integer.valueOf(8);
        Integer i2 = Integer.valueOf(128);
        Integer i3 = Integer.valueOf(155);
        String s1 = "hello";
        String s2 = "world";
        String s3 = "bloomfilter";

        bloomFilter.put(i1);
        bloomFilter.put(i2);
        bloomFilter.put(s1);
        bloomFilter.put(s2);

        Assert.assertTrue("i1" , bloomFilter.mightContain(i1));
        Assert.assertTrue("i2" , bloomFilter.mightContain(i2));
        Assert.assertTrue("s1" , bloomFilter.mightContain(s1));
        Assert.assertTrue("s2" , bloomFilter.mightContain(s2));

        Assert.assertFalse("i3", bloomFilter.mightContain(i3));
        Assert.assertFalse("s3", bloomFilter.mightContain(s3));

        bloomFilter.clear(s1);
        Assert.assertFalse("s1 clear", bloomFilter.mightContain(s1));
        bloomFilter.clear(s2);
        Assert.assertFalse("s2 clear", bloomFilter.mightContain(s2));

        bloomFilter.put(null);
        Assert.assertTrue("null", bloomFilter.mightContain(null));
        bloomFilter.clear(null);
        Assert.assertFalse("null clear", bloomFilter.mightContain(null));
    }

    @Test
    public void test2() {
        BloomFilter bloomFilter = new BloomFilter();
        StringBuilder url = new StringBuilder("http://lewiszlw.tech/");
        for (int i = 0; i < 10000; i++) {
            url.append(i);
            bloomFilter.put(url.toString());
        }
        Assert.assertFalse(bloomFilter.mightContain("http://lewiszlw.tech/"));
    }
}
