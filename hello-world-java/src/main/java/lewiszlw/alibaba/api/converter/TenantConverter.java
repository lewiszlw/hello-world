package lewiszlw.alibaba.api.converter;

import lewiszlw.alibaba.api.req.RentingReq;
import lewiszlw.alibaba.domain.tenant.Tenant;

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
