package lewiszlw.datastructure;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-08
 */
public class MinHeap {

    private Integer[] heap;

    private int size;

    public MinHeap(int cap) {
        this.heap = new Integer[cap];
    }

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * true upon success and throwing an Exception
     * if no space is currently available.
     */
    public boolean add(Integer e) {
        if (size == heap.length) {
            throw new RuntimeException("Heap is full");
        }

        Integer tmp = e;
        int i = 0;
        while (i < this.heap.length) {
            if (tmp > this.heap[i]) {

            } else {

            }
        }
        return false;

    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns null if this queue is empty.
     */
    public Integer peek() {
        return size == 0 ? null : heap[0];
    }

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from poll only in that it throws an exception if this
     * queue is empty.
     */
    public Integer remove() {
        return null;
    }

    /**
     * Retrieves and removes the head of this queue,
     * or returns null if this queue is empty.
     */
    public Integer poll() {
        return null;
    }

}
