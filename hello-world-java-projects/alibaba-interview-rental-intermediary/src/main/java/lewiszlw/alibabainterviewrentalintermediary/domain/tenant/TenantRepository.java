package lewiszlw.alibabainterviewrentalintermediary.domain.tenant;

import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
public interface TenantRepository {

    /**
     * 查询所有未租的顾客
     */
    List<Tenant> findNotRent();

    /**
     * 登记
     */
    void register(Tenant tenant);
}
