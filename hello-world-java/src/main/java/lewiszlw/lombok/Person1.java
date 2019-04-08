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
 * @Accessors(fluent = true)
 * 属性pepper的getter就是 pepper()，setter方法就是pepper(T newValue)
 * setter返回this
 */
@Data
@Accessors(fluent = true)
public class Person1 {

    private int age;

    private String name;
}
