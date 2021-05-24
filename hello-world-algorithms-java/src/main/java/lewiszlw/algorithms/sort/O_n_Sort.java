package lewiszlw.algorithms.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * O(n)时间复杂度排序
 *
 * 给定n(0<n<50)个 0-100之间的数，使用O(n)时间复杂度排序
 */

public class O_n_Sort {

    /**
     * 解法：利用数组下班
     * 将数字映射到另一个数组下标
     */
    public void sort(int[] nums) {
        // 下标为nums[i]，元素为出现次数
        Integer[] arr = new Integer[101]; // 因为nums[i]最大值为100

        for (int i = 0; i < nums.length; i++) {
            if (arr[nums[i]] == null) {
                arr[nums[i]] = 1;
            } else {
                arr[nums[i]] += 1;
            }
        }

        // 遍历arr，覆盖nums
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                continue;
            }
            for (int j = 0; j < arr[i]; j++) {
                nums[index] = i;
                index ++;
            }
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{2,5,4,1,6,8,2,5,10};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
