package lewiszlw.problems;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Desc: TopK问题：从 N (N>0) 个数中找出最大的 K (K <= N) 个数
 *
 * @author zhanglinwei02
 * @date 2019-04-04
 */
public class TopK {

    /**
     * 解法一：快速排序+遍历 O(NlogN + k)
     */
    public int[] solution1(int[] nums, int k) {
        Arrays.sort(nums);
        int[] result = new int[k];
        for (int i = nums.length - 1; i > nums.length - 1 - k; i--) {
            result[nums.length - 1 - i] = nums[i];
        }
        return result;
    }


    /**
     * 解法二：基于快排思想 O(NlogK)
     * 在数组中随机找一个元素pivot，将数组分成两部分Sa和Sb，其中Sa的元素>=pivot，Sb的元素<pivot，然后分析两种情况：
     *  若Sa中元素的个数大于或等于K，则在Sa中查找最大的K个数
     *  若Sa中元素的个数小于K，其个数为len，则在Sb中查找K-len个数字
     * 如此递归下去，不断把问题分解为更小的问题，直到求出结果。
     */
    public int[] solution2(int[] nums, int k) {
        return null;
    }
    private void quickSort(int[] nums, int pivot) {

    }


    /**
     * 解法三：最小堆 O(NlogK)
     * 使用最小堆来存储最大的K个元素。最小堆的堆顶元素就是最大K个数中最小的一个。
     * 每次考虑下一个数x时，如果x比堆顶元素小，则不需要改变原来的堆。如果想x比堆顶元素大，
     * 那么用x替换堆顶元素，同时移动堆节点保持最小堆性质
     */
    public int[] solution3(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue();
        return null;
    }
    @Test
    public void test1() {
        System.out.println(Arrays.toString(solution1(new int[]{2, 6, 3, 9, 7}, 3)));
    }

}
