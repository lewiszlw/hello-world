package lewiszlw.alibaba.api.req;

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
public class RentingReq {

    /**顾客基本信息*/
    private String name;
    private String phone;

    /**顾客租房要求*/
    private Long amountMin;
    private Long amountMax;
    private Integer bedroomMin;
    private Integer bedRoomMax;
    private Integer livingRoomMin;
    private Integer livingRoomMax;
}
