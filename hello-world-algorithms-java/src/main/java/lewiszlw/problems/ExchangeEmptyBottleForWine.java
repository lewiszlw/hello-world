package lewiszlw.problems;

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 * 空瓶换酒。想喝N瓶酒，每K个空瓶换一瓶新酒，问需要花费几瓶酒钱？
 *
 * @author zhanglinwei02
 * @date 2019-06-11
 */
public class ExchangeEmptyBottleForWine {

    /**
     * 解法一：模拟喝酒过程
     */
    public int solution1(int N, int K) {
        // 实际花费瓶数
        int realSpend = 0;
        // 空瓶数
        int emptyBottles = 0;
        // 喝的瓶数
        int drink = 0;
        for (int i = 0; i < N; i++) {
            // 买一瓶, 空瓶数增加1
            realSpend ++;
            drink ++;
            emptyBottles ++;

            // 判断是否喝到N瓶酒
            if (drink == N) {
                return realSpend;
            }

            // 判断空瓶数能否兑换一瓶新酒
            if (emptyBottles == K) {
                drink ++;
                emptyBottles = 1;
            }

            // 判断是否喝到N瓶酒
            if (drink == N) {
                return realSpend;
            }
        }
        return -1;
    }

    /**
     *
     */
    public int solution2(int N, int K) {
        return 0;
    }

    @Test
    public void test() {
        Assert.assertTrue(solution1(10, 3) == 7);
    }
}
