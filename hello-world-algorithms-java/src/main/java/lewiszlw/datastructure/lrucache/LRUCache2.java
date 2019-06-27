package lewiszlw.datastructure.lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc: LRU (Least Recently Used) 最近最少使用
 * HashMap + 双向链表 实现
 * HashMap的value指向双向链表节点, 这样放入和移除都是 O(1)
 *
 * 7 0 1 2 0 3 0 4
 * ---------------
 *     1 2 0 3 0 4
 *   0 0 1 2 0 3 0
 * 7 7 7 0 1 2 2 3
 *
 * @author zhanglinwei02
 * @date 2019-04-03
 */

public class LRUCache2<K, V> {

    /**
     * 存放key和节点，查询复杂度为O(1)
     */
    private Map<K, DLinkedNode<K, V>> cacheMap;

    /**
     * cache容量
     */
    private int capacity;

    /**
     * 双向链表头结点
     */
    private DLinkedNode<K, V> head;

    /**
     * 双向链表尾节点
     */
    private DLinkedNode<K, V> tail;

    public LRUCache2 (int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Illegal initial capacity: " + capacity);
        }
        this.capacity = capacity;
        cacheMap = new HashMap<>(capacity + 1);
        initHeadTail();
    }

    /**
     * 初始化头尾节点
     */
    private void initHeadTail() {
        head = new DLinkedNode<>(null, null);
        tail = new DLinkedNode<>(null, null);
        head.post = tail;
        tail.pre = head;
    }

    public void put(K key, V val) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (cacheMap.containsKey(key)) {
            DLinkedNode<K, V> node = cacheMap.get(key);
            // 更新node的值
            node.val = val;
            // 移除node
            remove(node);
            // 插入到头部
            insertToHead(node);
        } else {
            DLinkedNode<K, V> node = new DLinkedNode<>(key, val);
            // 存入map
            cacheMap.put(key, node);
            // 插入到头部
            insertToHead(node);
            if (cacheMap.size() > capacity) {
                // 清除尾部节点
                removeTailPre();
            }
        }
    }

    public V get(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (cacheMap.containsKey(key)) {
            DLinkedNode<K, V> node = cacheMap.get(key);
            // 移除node
            remove(node);
            // 插入到头部
            insertToHead(node);
            return node.val;
        }
        return null;
    }

    /**
     * 清除尾部节点
     */
    private void removeTailPre() {
        DLinkedNode<K, V> delNode = tail.pre;
        DLinkedNode<K, V> tailPrePre = delNode.pre;
        tailPrePre.post = tail;
        tail.pre = tailPrePre;
        delNode.pre = null;
        delNode.post = null;
        cacheMap.remove(delNode.key);
    }

    /**
     * 插入节点到链表头部
     */
    private void insertToHead(DLinkedNode<K, V> node) {
        DLinkedNode<K, V> oldHeadPostNode = head.post;
        head.post = node;
        node.pre = head;
        node.post = oldHeadPostNode;
        oldHeadPostNode.pre = node;
    }

    /**
     * 移除node节点
     */
    private void remove(DLinkedNode<K, V> node) {
        DLinkedNode<K, V> preNode = node.pre;
        DLinkedNode<K, V> postNode = node.post;
        preNode.post = postNode;
        postNode.pre = preNode;
        node.pre = null;
        node.post = null;
    }


    public boolean containsKey(K key) {
        return cacheMap.containsKey(key);
    }

    public int size() {
        return cacheMap.size();
    }

    /**
     * 双向链表节点
     */
    private class DLinkedNode<K, V> {
        K key;  // 便于从map中删除
        V val;
        DLinkedNode pre;
        DLinkedNode post;
        DLinkedNode(K key, V val) {this.key = key; this.val = val;}
    }
}
