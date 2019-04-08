package lewiszlw.lombok;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-08
 */

/**
 * @Accessors(chain = true)
 * setter返回值为this而不是void
 */
@Data
@Accessors(chain = true)
public class Person2 {

    private int age;

    private String name;
}
