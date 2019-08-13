package lewiszlw.dubbo.demo.iface;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-25
 */
@Data
@Accessors(chain = true)
public class HelloRequestDTO implements Serializable {

    private String name;

    private String words;
}
