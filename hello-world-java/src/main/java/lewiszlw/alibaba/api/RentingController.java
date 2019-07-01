package lewiszlw.alibaba.api;

import com.sun.tools.internal.ws.wsdl.parser.W3CAddressingExtensionHandler;
import lewiszlw.alibaba.api.converter.TenantConverter;
import lewiszlw.alibaba.api.req.RentingReq;
import lewiszlw.alibaba.api.resp.WebResponse;
import lewiszlw.alibaba.application.RentingApplicationService;
import lewiszlw.alibaba.domain.tenant.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
@Controller
public class RentingController {

    @Autowired
    private RentingApplicationService rentingApplicationService;

    /**
     * 顾客租房api
     */
    public WebResponse renting(RentingReq rentingReq) {

        // RentingReq 包含顾客基本信息、租房要求
        // 举例:
//        RentingReq rentingReq1 = new RentingReq()
//                .setName("小张")
//                .setPhone("15088888888")
//                .setAmountMin(0L)
//                .setAmountMax(600000L)
//                .setBedroomMin(3)
//                .setBedRoomMax(3)
//                .setLivingRoomMin(2)
//                .setLivingRoomMax(2);
//
//        RentingReq rentingReq2 = new RentingReq()
//                .setName("小李")
//                .setPhone("15088888881")
//                .setAmountMin(0L)
//                .setAmountMax(150000L)
//                .setBedroomMin(0)
//                .setBedRoomMax(Integer.MAX_VALUE)
//                .setLivingRoomMin(0)
//                .setLivingRoomMax(Integer.MAX_VALUE);

        // 转换为领域模型
        Tenant tenant = TenantConverter.convertToTenant(rentingReq);
        // 登记顾客信息
        rentingApplicationService.registerTenant(tenant);

        return WebResponse.createSuccessWebResponse();
    }
}
