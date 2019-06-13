package lewiszlw.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Desc: 冒泡排序
 *
 * 两个数比较大小，较大的数下沉，较小的数冒起来。
 *
 * @author zhanglinwei02
 * @date 2019-06-13
 */
public class BubbleSort {

    /**
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对第0个到第n-1个数据做同样的工作。这时，最大的数就“浮”到了数组最后的位置上。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */
    public void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    // 较大数上浮
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
    }


    /**
     * 优化：
     * 每次遍历时，比较相邻两位，较大数上浮，同时记录交换次数，每遍历一次，会将最大数上浮到最高处，
     * 所以下次遍历无需遍历尾部，当交换次数为0，代表已全部升序排列
     */
    public void sort2(int[] arr) {
        int end = arr.length - 1;
        while (end > 0) {
            // 记录交换次数
            int exchange = 0;
            for (int i = 0; i < end; i++) {
                // 比较相邻两位，较大数上浮
                if (arr[i] > arr[i + 1]) {
                    exchange ++;
                    int tmp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = tmp;
                }
            }
            // 交换次数为0，代表已经全部升序排列
            if (exchange == 0) {
                break;
            }
            // 当前遍历后的最后一个数一定最大，所以下次遍历不用再遍历
            end --;
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

    @Test
    public void test2() {
        int[] arr = {3, 5, 1, 4, 9, 5, 2, 8};
        sort2(arr);
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
