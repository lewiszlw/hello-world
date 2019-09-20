package lewiszlw.json.jsondiff;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/11/1
 * Time:15:28
 */
@Data
@AllArgsConstructor
public class NewOldValue {
    private String newVal;

    private String oldVal;
}
