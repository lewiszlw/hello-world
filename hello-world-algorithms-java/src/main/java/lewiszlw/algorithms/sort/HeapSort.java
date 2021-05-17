package lewiszlw.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 堆排序
 * 思想：
 * 将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。
 * 将其与末尾元素进行交换，此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，
 * 这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
 *
 * 堆总是满足下列性质：
 * 1.堆中某个结点的值总是不大于（或不小于）其父结点的值；
 * 2.堆总是一棵完全二叉树。
 * 根结点最大的堆叫做 最大堆 或 大根堆，根结点最小的堆叫做 最小堆 或 小根堆。
 *
 *
 * 完全二叉树的特点：
 * 叶子结点只能出现在最下层和次下层，且最下层的叶子结点集中在树的左部。
 * 需要注意的是，满二叉树肯定是完全二叉树，而完全二叉树不一定是满二叉树。
 *
 * 完全二叉树举例
 *        a
 *       / \
 *      b   c
 *     / \
 *    d   e
 *
 * 满二叉树举例
 *         a
 *       /  \
 *      b    c
 *     / \  / \
 *    d   e f  g
 *
 * 最大堆举例
 *         50
 *        /  \
 *      45    40
 *     / \   / \
 *    20 25 35 30
 *
 * 最小堆举例
 *         10
 *        /  \
 *      20    15
 *     /  \   / \
 *    25  50 30  40
 *
 */
public class HeapSort {

    public void sort(int[] arr){

        // 建立最大堆
        for(int i = (arr.length - 1) / 2; i >= 0; i--){
            // 此时是完全无序堆，从最后一个非叶节点从右往左从下往上遍历，即从最底部最右部的第一个子树开始不停堆化，直至堆化整棵树
            heapify(arr, i, arr.length);
        }

        for(int heapLen = arr.length; heapLen > 1; heapLen--){
            // 堆顶（最大值）与末尾元素交换
            swap(arr, 0, heapLen - 1);
            // 除掉末尾元素，重新堆化，堆顶元素不断下沉到合适位置（此时的堆除了堆顶元素，其他元素未变化，即部分符合堆）
            heapify(arr, 0, heapLen - 1);
        }
    }

    // 堆化当前节点parent下属子树
    public void heapify(int[] arr, int parent, int len){
        int leftChild = 2 * parent + 1;	// 左子节点
        int rightChild = leftChild + 1;  // 右子节点
        int largest = parent;

        // 在父节点和左右子节点中最大的那个
        if (leftChild < len && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }
        if (rightChild < len && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }
        if (largest != parent) {
            // 原堆顶元素下移
            swap(arr, parent, largest);
            // 继续调整原堆顶元素下属子树
            heapify(arr, largest, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Test
    public void test() {
        int[] arr = {3, 5, 1, 4, 9, 5, 2, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
        Assert.assertTrue(arr[0] == 1);
        Assert.assertTrue(arr[1] == 2);
        Assert.assertTrue(arr[2] == 3);
        Assert.assertTrue(arr[3] == 4);
        Assert.assertTrue(arr[4] == 5);
        Assert.assertTrue(arr[5] == 5);
        Assert.assertTrue(arr[6] == 8);
        Assert.assertTrue(arr[7] == 9);
    }
}
