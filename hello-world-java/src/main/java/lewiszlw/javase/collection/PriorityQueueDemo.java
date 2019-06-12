package lewiszlw.javase.collection;

import java.util.PriorityQueue;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-10
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(3);
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(2);
        priorityQueue.add(-1);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
    }
}
