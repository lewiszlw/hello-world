package lewiszlw.spi;

import java.util.ServiceLoader;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-19
 */
public class SpiDemo {

    public static void main(String[] args) {
        // 配置文件,需放置在META-INF/services/接口全限定名
        // 通过改变配置文件,我们就能动态的改变一个接口的实现类
        ServiceLoader<ISayName> iSayNames = ServiceLoader.load(ISayName.class);
        for (ISayName isayName : iSayNames) {
            isayName.sayName();
        }
    }
}
