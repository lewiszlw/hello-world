package lewiszlw.algorithms;

import java.util.BitSet;

/**
 * Desc: 布隆过滤器
 *
 * @author zhanglinwei02
 * @date 2019-03-30
 */
public class BloomFilter {

    /**
     * 默认初始化容量
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 20;

    /**
     * bitmap容量值
     */
    private int capacity;

    /**
     * 采用BigSet作为bitmap
     */
    private BitSet bitmap;

    public BloomFilter() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public BloomFilter(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + capacity);
        }
        this.capacity = capacity;
        bitmap = new BitSet(capacity);
    }

    /**
     * 存放一个元素
     */
    public void put(Object o) {
        bitmap.set(bitIndex(o));
    }

    /**
     * 清除一个元素
     */
    public void clear(Object o) {
        bitmap.clear(bitIndex(o));
    }

    /**
     * 是否包含该元素
     */
    public boolean mightContain(Object o) {
        return bitmap.get(bitIndex(o));
    }

    /**
     * 计算hash后在bitmap中的bitIndex
     */
    private int bitIndex(Object o) {
        int h = hash(o);
        return h & (capacity - 1);
    }

    /**
     * HashMap使用的hash函数
     */
    static final int hash(Object o) {
        int h;
        return (o == null) ? 0 : (h = o.hashCode()) ^ (h >>> 16);
    }

}
