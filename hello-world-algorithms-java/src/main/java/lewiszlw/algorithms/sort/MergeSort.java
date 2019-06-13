package lewiszlw.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Desc: 归并排序
 *
 * 归并排序的思想就是先递归分解数组，再合并数组。
 *
 * @author zhanglinwei02
 * @date 2019-06-13
 */
public class MergeSort {

    /**
     * 本思路是将数组分解成left和right，如果这两个数组内部数据是有序的，
     * 那么就可以用上面合并数组的方法将这两个数组合并排序。如何让这两个数组内部是有序的？
     * 可以再二分，直至分解出的小组只含有一个元素时为止，此时认为该小组内部已有序。
     * 然后合并排序相邻二个小组即可。
     */
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 左右两边排序
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            // 合并两边升序数组
            merge(arr, left, mid, mid + 1, right);
        }
    }

    /**
     * 合并两个升序数组
     */
    private void merge(int[] arr, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        // 临时数组，存放排序后数组
        int[] tmp = new int[arr.length];
        int index = 0;
        int i = leftStart, j = rightStart;
        // 合并前面
        while (i <= leftEnd && j <= rightEnd) {
            if (arr[i] < arr[j]) {
                tmp[index ++] = arr[i ++];
            } else {
                tmp[index ++] = arr[j ++];
            }
        }
        // 多余尾部
        if (i <= leftEnd) {
            while (i <= leftEnd) {
                tmp[index ++] = arr[i ++];
            }
        }
        if (j <= rightEnd) {
            while (j <= rightEnd) {
                tmp[index ++] = arr[j ++];
            }
        }
        // 复制到原数组arr中
        for (int k = 0; k < index; k++) {
            arr[leftStart + k] = tmp[k];
        }
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
