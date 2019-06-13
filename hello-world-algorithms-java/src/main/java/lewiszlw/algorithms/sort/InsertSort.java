package lewiszlw.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Desc: 插入排序
 *
 * 在要排序的一组数中，假定前n-1个数已经排好序，现在将第n个数插到前面的有序数列中，
 * 使得这n个数也是排好顺序的。如此反复循环，直到全部排好顺序。
 *
 * @author zhanglinwei02
 * @date 2019-06-13
 */
public class InsertSort {

    /**
     * 从第一个元素开始，该元素可以认为已经被排序
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 如果被扫描的元素（已排序）大于新元素，将该元素后移一位
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 将新元素插入到该位置后
     * 重复步骤2~5
     */
    public void sort(int[] arr) {
        // 当前待插入的数
        int currIndex = 0;
        while (currIndex < arr.length) {
            for (int i = currIndex; i >= 0; i--) {
                // 不断交换，找到合适的位置停止
                if (i - 1 >= 0 && arr[i - 1] > arr[i]) {
                    int tmp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = tmp;
                } else {
                    break;
                }
            }
            currIndex ++;
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
