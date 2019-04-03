package lewiszlw.jmockit;

import mockit.Expectations;
import mockit.Verifications;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-03
 */
public class VerificationTest {
    @Test
    public void testVerification() {
        // 录制阶段
        Calendar cal = Calendar.getInstance();
        new Expectations(Calendar.class) {
            {
                // 对cal.get方法进行录制，并匹配参数 Calendar.YEAR
                cal.get(Calendar.YEAR);
                result = 2016;// 年份不再返回当前小时。而是返回2016年
                cal.get(Calendar.HOUR_OF_DAY);
                result = 7;// 小时不再返回当前小时。而是返回早上7点钟
            }
        };
        // 重放阶段
        Calendar now = Calendar.getInstance();
        Assert.assertTrue(now.get(Calendar.YEAR) == 2016);
        Assert.assertTrue(now.get(Calendar.HOUR_OF_DAY) == 7);
        // 验证阶段
        new Verifications() {
            {
                Calendar.getInstance();
                // 限定上面的方法只调用了1次，当然也可以不限定
                times = 1;
                cal.get(anyInt);
                // 限定上面的方法只调用了2次，当然也可以不限定
                times = 2;
            }
        };

    }
}
