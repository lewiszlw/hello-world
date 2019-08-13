package lewiszlw.alibabainterviewrentalintermediary.api.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
@Data
@Accessors(chain = true)
public class WebResponse {

    private Integer code;

    private String message;

    public static WebResponse createSuccessWebResponse() {
        return new WebResponse().setCode(200).setMessage("success");
    }
}
