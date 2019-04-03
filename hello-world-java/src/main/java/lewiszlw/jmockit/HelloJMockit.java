package lewiszlw.jmockit;

import java.util.Locale;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-03
 */
public class HelloJMockit {

    public String sayHello() {
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA)) {
            // 在中国，就说中文
            return "你好，JMockit!";
        } else {
            // 在其它国家，就说英文
            return "Hello，JMockit!";
        }
    }
}
