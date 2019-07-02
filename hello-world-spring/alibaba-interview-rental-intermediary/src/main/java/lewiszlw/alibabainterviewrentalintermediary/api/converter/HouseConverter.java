package lewiszlw.alibabainterviewrentalintermediary.api.converter;

import lewiszlw.alibabainterviewrentalintermediary.api.req.AddHouseReq;
import lewiszlw.alibabainterviewrentalintermediary.domain.house.House;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
public class HouseConverter {

    public static House convertToHouse(AddHouseReq addHouseReq) {
        // 将AddHouseReq转换为领域模型
        return new House();
    }
}
