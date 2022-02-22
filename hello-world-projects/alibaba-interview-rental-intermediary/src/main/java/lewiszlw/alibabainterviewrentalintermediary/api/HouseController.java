package lewiszlw.alibabainterviewrentalintermediary.api;

import lewiszlw.alibabainterviewrentalintermediary.api.converter.HouseConverter;
import lewiszlw.alibabainterviewrentalintermediary.api.req.AddHouseReq;
import lewiszlw.alibabainterviewrentalintermediary.api.resp.WebResponse;
import lewiszlw.alibabainterviewrentalintermediary.application.HouseApplicationService;
import lewiszlw.alibabainterviewrentalintermediary.domain.house.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
@Controller
public class HouseController {

    @Autowired
    private HouseApplicationService houseApplicationService;

    /**
     * 添加新房api
     */
    public WebResponse addHouse(AddHouseReq addHouseReq) {
        // 将AddHouseReq转换为领域模型
        House house = HouseConverter.convertToHouse(addHouseReq);
        // 添加新房子
        houseApplicationService.addHouse(house);
        return WebResponse.createSuccessWebResponse();
    }
}
