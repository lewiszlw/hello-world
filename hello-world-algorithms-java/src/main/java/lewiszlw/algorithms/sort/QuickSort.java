package lewiszlw.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Desc: 快速排序
 *
 * 分治法思想
 *
 * @author zhanglinwei02
 * @date 2019-06-13
 */
public class QuickSort {

    /**
     * 步骤:
     * 从数列中挑出一个元素作为基准数；
     * 分区过程，将比基准数大的放到右边，小于或等于它的数都放到左边；
     * 再对左右区间递归执行第二步，直至各区间只有一个数。
     *
     *
     * 时间复杂度:
     * 最好的情况是枢纽元选取得当，每次都能均匀的划分序列。 时间复杂度O(nlogn)
     * 最坏情况是枢纽元为最大或者最小数字，那么所有数都划分到一个序列去了 时间复杂度为O(n^2)
     *
     * 优化:
     * 1.在partition中选取pivot时，选取首尾中的进行比较，选取中位数为pivot，
     *   以保证pivot能够尽可能的固定在中间，而让两端递归的子数组更加均衡。
     * 2.当待排序序列长度分割到一定大小后，使用插入排序。
     * 3.在一次分割结束后，可以把与Key相等的元素聚在一起，继续下次分割时，
     *   不用再对与key相等元素分割（处理重复效率极高）。
     */
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left, j = right;
        int key = arr[i];
        while (i < j) {
            // 从最右边j开始（由于key是取最左边数）
            while (i < j && arr[j] >= key) {
                j --;
            }
            // 此时可能 i >=j 或 arr[j] < key
            if (i < j) {
                // 将右侧小于key的数移到左侧，丢失的数存放在key中
                arr[i] = arr[j];
                i ++;
            }
            // 从左边开始，遍历出第一个大于等于key的数
            while (i < j && arr[i] < key) {
                i ++;
            }
            // 此时可能 i >=j 或 arr[i] >= key
            if (i < j) {
                // 将左侧大于等于key的值移到j处，j处的数已存放在左侧
                arr[j] = arr[i];
                j --;
            }
        }
        // 把丢失的数填上
        arr[i] = key;

        // 递归左右子区间
        sort(arr, left, i);
        sort(arr, i + 1, right);
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
