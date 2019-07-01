package lewiszlw.alibaba.domain.tenant;

import lewiszlw.alibaba.domain.house.House;
import lewiszlw.alibaba.domain.tenant.constant.TenantInfoType;
import lewiszlw.alibaba.domain.tenant.requirement.AbstractRequirement;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Desc: 顾客领域模型
 *
 * @author zhanglinwei02
 * @date 2019-06-30
 */
public class Tenant {

    /** 顾客id */
    Integer tenantId;

    /** 顾客基本信息 **/
    Map<TenantInfoType, String> basicInfos;

    /** 租房要求 */
    List<AbstractRequirement> requirements;

    /** 是否已租 */
    boolean hasRent;

    /**
     * 判断房屋是否满足需求
     */
    public boolean satisfy(House house) {
        if (CollectionUtils.isEmpty(requirements)) {
            return true;
        }
        for (AbstractRequirement requirement : requirements) {
            if (!requirement.satisfy(house)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取顾客某项基本信息
     */
    public String getBasicInfo(TenantInfoType tenantInfoType) {
        if (CollectionUtils.isEmpty(basicInfos) || tenantInfoType == null) {
            return null;
        }
        return basicInfos.get(tenantInfoType);
    }
}
