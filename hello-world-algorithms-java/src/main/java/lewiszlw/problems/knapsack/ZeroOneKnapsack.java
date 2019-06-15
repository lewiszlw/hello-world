package lewiszlw.problems.knapsack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc: 01背包问题
 *
 * 有N个物品，每个物品的重量为w[i]，每个物品的价值为v[i]。现在有一个背包，它所能容纳的重量为W
 * 问：如何让背包里装入的物品具有最大的价值总和？
 *
 * @author zhanglinwei02
 * @date 2019-06-15
 */
public class ZeroOneKnapsack {

    /**
     * 解法一：动态规划
     * dp表：横坐标是重量，纵坐标是最大价值
     * 状态转移方程: dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]])
     * 即j重量下的最大价值为，当前物品价值v[i] + (j-当前物品重量w[i]) 重量下的最大价值 和 dp[j] 的比较取大者。
     *
     * @param w 每个物品重量
     * @param v 每个物品价值
     * @param W 背包最大可容纳重量
     * @return 背包物品最大价值总和
     */
    public int solution1(int[] w, int[] v, int W) {
        // 索引为重量，数值为价值
        int[] dp = new int[W + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 0;
        }

        // 物品数
        int N = w.length;

        for (int i = 0; i < N; i++) {
            int currItemWeight = w[i];
            // 从最大重量 W 遍历到当前最小重量currItemWeight
            for (int j = W; j >= currItemWeight; j--) {
                // 当前物品价值v[i] + (j - currItemWeight)重量对应的最大价值
                dp[j] = Math.max(dp[j], v[i] + dp[j - currItemWeight]);
            }
        }

        // 重量为W的最大价值
        return dp[W];
    }


    @Test
    public void test() {
        int maxValue = solution1(new int[]{10,20,30}, new int[]{60,100,120}, 50);
        Assert.assertTrue(maxValue == 220);
    }
}
