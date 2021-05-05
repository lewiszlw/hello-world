package lewiszlw.problems;

import org.junit.Assert;
import org.junit.Test;

/**
 * 题目：一个数组中只有一个数字出现了一次，所有其他数字都出现了两次，找出这个只出现一次的数字
 * Example
 * Input: [1,1,2,6,6,5,5,9,9]
 * Output: 2
 */

public class FindNumberOccurringOnce {

    /**
     * 解法：异或运算
     * 异或运算的三个特性，即交换律，x与0异或结果是x，x与x异或结果是0
     *  a^b^a = (a^a)^b = 0^b = b
     */
    public int occurringOnceNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    @Test
    public void test() {
        Assert.assertEquals(2, occurringOnceNumber(new int[]{1,1,2,6,6,5,5,9,9}));
        Assert.assertEquals(1, occurringOnceNumber(new int[]{1}));
    }
}
