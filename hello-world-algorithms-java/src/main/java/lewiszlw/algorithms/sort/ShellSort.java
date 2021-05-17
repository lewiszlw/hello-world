package lewiszlw.algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 希尔排序，也称递减增量排序算法，实质是分组插入排序。
 * 在要排序的一组数中，根据某一增量分为若干子序列，并对子序列分别进行插入排序。
 * 然后逐渐将增量减小,并重复上述过程。直至增量为1,此时数据序列基本有序,最后进行插入排序。
 */
public class ShellSort {

    public void sort(int[] arr) {
        if(arr==null || arr.length == 0){
            return;
        }

        int incre=arr.length;

        while(incre != 1){

            // 每次增量减半，直至为1
            incre = incre / 2;

            // 遍历每个子序列
            for(int k = 0; k < incre; k++){

                // 对子序列进行插入排序
                // 遍历当前子序列除第一个外的每个值
                for(int i = k + incre; i < arr.length; i = i + incre){

                    // 不停交换 arr[i] 和 arr[j - incre]
                    for(int j = i; j > k; j = j - incre){
                        if(arr[j - incre] > arr[j]){
                            int temp = arr[j - incre];
                            arr[j - incre] = arr[j];
                            arr[j] = temp;
                        }else {
                            break;
                        }
                    }
                }
            }
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
