package lewiszlw.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Desc: 蓄水池采样算法
 *
 * 给定一个数据流，数据流长度 N 很大，且 N 直到处理完所有数据之前都不可知，
 * 请问如何在只遍历一遍数据（O(N)）的情况下，能够随机选取出 m 个不重复的数据。
 *
 * @author zhanglinwei02
 * @date 2019-06-13
 */
public class ReservoirSampling {

    /**
     * 思路：
     * 如果接收的数据量小于 m，则依次放入蓄水池。
     * 当接收到第 i 个数据时，i >= m，在[0, i]范围内取以随机数 d，若 d 的落在[0, m-1]范围内，则用接收到的第 i 个数据替换蓄水池中的第 d 个数据。
     * 重复上述步骤。
     *
     * 算法的精妙之处在于：
     * 当处理完所有的数据时，蓄水池中的每个数据都是以 m/N 的概率获得的。
     */
    public int[] sampling(int[] dataStream, int m) {
        // 初始化蓄水池
        int[] reservoir = new int[m];
        for (int i = 0; i < m; i++) {
            reservoir[i] = dataStream[i];
        }

        Random random = new Random();
        for (int i = m; i < dataStream.length; i++) {
            // [0, i]取随机数
            int d = random.nextInt(i + 1);
            if (d < m) {
                reservoir[d] = dataStream[i];
            }
        }
        return reservoir;
    }

    /**
     * 分布式蓄水池抽样
     *
     * 1.假设有K台机器，将大数据集分成K个数据流，每台机器使用单机版蓄水池抽样处理一个数据流，
     *   抽样m个数据，并最后记录处理的数据量为N1, N2, ..., Nk, ..., NK(假设m<Nk)。N1+N2+...+Nk=N。
     * 2.取[1, N]一个随机数d，若d<N1，则在第一台机器的蓄水池中等概率不放回地（1/m）选取一个数据；
     *   若N1<=d<(N1+N2)，则在第二台机器的蓄水池中等概率不放回地选取一个数据；一次类推，重复m次，则最终从N大数据集中选出m个数据。
     */


    @Test
    public void test() {
        int[] result = sampling(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 5);
        System.out.println(Arrays.toString(result));
    }
}
