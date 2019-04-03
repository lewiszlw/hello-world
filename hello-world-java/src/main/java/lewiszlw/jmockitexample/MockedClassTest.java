package lewiszlw.jmockitexample;

import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-03
 */
public class MockedClassTest {
    // 加上了JMockit的API @Mocked, JMockit会帮我们实例化这个对象，不用担心它为null
    @Mocked
    Locale locale;

    // 当@Mocked作用于class
    @Test
    public void testMockedClass() {
        // 静态方法不起作用了,返回了null
        Assert.assertTrue(Locale.getDefault() == null);
        // 非静态方法（返回类型为String）也不起作用了，返回了null
        Assert.assertTrue(locale.getCountry() == null);
        // 自已new一个，也同样如此，方法都被mock了
        Locale chinaLocale = new Locale("zh", "CN");
        Assert.assertTrue(chinaLocale.getCountry() == null);
    }
}
