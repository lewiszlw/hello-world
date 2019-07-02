package lewiszlw.alibabainterviewrentalintermediary.api.converter;

import lewiszlw.alibabainterviewrentalintermediary.api.req.RentingReq;
import lewiszlw.alibabainterviewrentalintermediary.domain.tenant.Tenant;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
public class TenantConverter {

    public static Tenant convertToTenant(RentingReq rentingReq) {
        // 将顾客租房信息转换为领域模型
        return new Tenant();
    }
}
