package lewiszlw.algorithms.sort;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Desc: 桶排序
 *
 * 将数据分到有限数量的桶里，然后每个桶再分别排序（有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序）。
 *
 * @author zhanglinwei02
 * @date 2019-06-13
 */
public class BucketSort {

    /**
     * 如果取到合理的桶值，根据映射函数，我们可以将待排序数组均匀的分到每个桶中，
     * 再分别对各个桶中的元素进行排序，最后我们直接输出桶中的数据就好。
     */
    public void sort(int[] arr) {
        // 找到最大值最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
            min = Math.min(arr[i], min);
        }

        // 建立桶
        // 如max=5500，min=3，arr.length=1000，则桶数量为6，装[3 , arr.length+3]、[arr.length+3 , 2*arr.length+3]、
        // [2*arr.length+3 , 3*arr.length+3]、[3*arr.length+3 , 4*arr.length+3]、[4*arr.length+3 , 5*arr.length+3]、
        // [5*arr.length+3 , 6*arr.length+3]
        int bucketsNum = (max - min) / arr.length + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketsNum);
        for (int i = 0; i < bucketsNum; i++) {
            buckets.add(new ArrayList<>());
        }

        // 将arr中每个数分配到相应的桶中
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = arr[i] / arr.length;
            buckets.get(bucketIndex).add(arr[i]);
        }

        // 每个桶内排序
        for (int i = 0; i < bucketsNum; i++) {
            Collections.sort(buckets.get(i));
        }

        // 赋值到arr
        int index = 0;
        for (int i = 0; i < bucketsNum; i++) {
            List<Integer> bucket = buckets.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                arr[index ++] = bucket.get(j);
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
