package lewiszlw.javase.optional;

import lombok.Builder;
import lombok.ToString;

import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-02-26
 */
@Builder
@ToString
public class User {

    String name;

    List<Integer> orders;
}
