package lewiszlw.problems;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Desc:
 * N场表演，时间表以[{startTime, endTime}...]给出，计算最多能参加几场
 *
 * @author zhanglinwei02
 * @date 2019-06-11
 */
public class MaxPlaysToAttend {

    /**
     * 解法1：贪心算法
     * 先按照endTime进行排序，再顺序取合适场次
     */
    public int solution1(int[][] schedules) {
        Arrays.sort(schedules, Comparator.comparingInt(o -> o[1]));
        int max = 0;
        int time = 0;
        for (int i = 0; i < schedules.length; i++) {
            int startTime = schedules[i][0];
            int endTime = schedules[i][1];
            if (startTime >= time) {
                max ++;
                time = endTime;
            }
        }
        return max;
    }

    @Test
    public void test() {
        int[][] schedules = new int[][] {
                {1, 8},
                {5, 9},
                {10, 11},
                {8, 10},
                {3, 5},
                {6, 8},
        };
        Assert.assertTrue(solution1(schedules) == 4);
    }
}
