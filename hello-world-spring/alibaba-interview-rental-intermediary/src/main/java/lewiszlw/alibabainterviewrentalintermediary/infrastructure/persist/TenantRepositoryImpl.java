package lewiszlw.alibabainterviewrentalintermediary.infrastructure.persist;

import lewiszlw.alibabainterviewrentalintermediary.domain.tenant.Tenant;
import lewiszlw.alibabainterviewrentalintermediary.domain.tenant.TenantRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
@Component
public class TenantRepositoryImpl implements TenantRepository {

    @Override
    public List<Tenant> findNotRent() {
        // 查找DB，将DO模型转换为领域模型
        return null;
    }

    @Override
    public void register(Tenant tenant) {
        // 将领域模型转换为DO模型存储到DB
    }
}
