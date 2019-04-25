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
// 必须实现Serializable
public class HelloResponseDTO implements Serializable {

    private String name;

    private String resp;
}
