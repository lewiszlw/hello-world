package lewiszlw.datastructure.lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc: LRU (Least Recently Used) 最近最少使用
 * HashMap + 单链表 实现
 * 性能较低，get、put操作都要遍历链表，时间复杂度为 O(n)
 *
 * 7 0 1 2 0 3 0 4
 * ---------------
 *     1 2 0 3 0 4
 *   0 0 1 2 0 3 0
 * 7 7 7 0 1 2 2 3
 *
 * @author zhanglinwei02
 * @date 2019-04-02
 */
public class LRUCache1 {

    private Node head;

    private int capacity;

    private Map<Integer, Integer> map;

    public LRUCache1(int capacity) {
        this.capacity = capacity;
        head = new Node(0);
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int val = map.get(key);
            deleteNodeK(key);
            updateKToHeadNext(key, val);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            deleteNodeK(key);
        }
        updateKToHeadNext(key, value);
        if (map.size() > capacity) {
            deleteLastNode();
        }
    }

    /**
     * 删除链表最后一个节点
     */
    private void deleteLastNode() {
        Node p = head;
        while (p.next != null) {
            if (p.next.next == null) {
                map.remove(p.next.key);
                p.next = null;
                break;
            }
            p = p.next;
        }
    }

    /**
     * 删除链表为key的节点
     */
    private Node deleteNodeK(int key) {
        Node p = head;
        while (p.next != null) {
            if (p.next.key == key) {
                Node kNode = p.next;
                Node kNext = kNode == null? null: kNode.next;
                p.next = kNext;
                map.remove(key);
                return kNode;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 更新head的下一个节点为key
     */
    private void updateKToHeadNext(int key, int value) {
        Node tmp = head.next;
        Node k = new Node(key);
        head.next = k;
        k.next = tmp;
        map.put(key, value);
    }

    public void printCache() {
        Node p = head;
        while (p.next != null) {
            System.out.println(p.key + ", " + map.get(p.key));
            p = p.next;
        }
    }

    class Node {
        int key;
        Node next;
        Node(int key) {
            this.key = key;
        }
    }
}
