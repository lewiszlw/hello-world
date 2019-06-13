package lewiszlw.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Desc: 选择排序
 *
 * 不断遍历数组找到最小数字交换到数组前面
 *
 * @author zhanglinwei02
 * @date 2019-06-13
 */
public class SelectSort {

    /**
     * 在长度为n的无序数组中，第一次遍历n个数，找到最小的数值与第一个元素交换；
     * 第二次遍历n-1 (索引1 -> n-1)个数，找到最小的数值与第二个元素交换；
     * ……
     * 第n-1次遍历，找到最小的数值与第n-1个元素交换，排序完成。
     */
    public void sort(int[] arr) {
        int start = 0;
        while (start < arr.length) {
            // 找到start到数组末尾最小的数
            int minIndex = start;
            for (int i = start; i < arr.length; i++) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
            }

            // 将当前遍历最小数字移动到当前遍历首位
            int tmp = arr[start];
            arr[start] = arr[minIndex];
            arr[minIndex] = tmp;

            // 从下一位开始重新遍历
            start++;
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
