package lewiszlw.alibaba.api;

import lewiszlw.alibaba.api.converter.HouseConverter;
import lewiszlw.alibaba.api.req.AddHouseReq;
import lewiszlw.alibaba.api.resp.WebResponse;
import lewiszlw.alibaba.application.HouseApplicationService;
import lewiszlw.alibaba.domain.house.House;
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
