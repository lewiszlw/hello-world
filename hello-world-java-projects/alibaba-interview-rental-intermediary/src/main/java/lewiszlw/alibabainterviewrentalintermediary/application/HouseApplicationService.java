package lewiszlw.alibabainterviewrentalintermediary.application;

import lewiszlw.alibabainterviewrentalintermediary.domain.house.House;
import lewiszlw.alibabainterviewrentalintermediary.domain.house.HouseRepository;
import lewiszlw.alibabainterviewrentalintermediary.domain.tenant.Tenant;
import lewiszlw.alibabainterviewrentalintermediary.domain.tenant.TenantRepository;
import lewiszlw.alibabainterviewrentalintermediary.domain.tenant.constant.TenantInfoType;
import lewiszlw.alibabainterviewrentalintermediary.infrastructure.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
@Service
public class HouseApplicationService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private MessageService messageService;

    /**
     * 添加新房
     */
    public void addHouse(House house) {
        // 判断是否满足某些顾客要求
        List<Tenant> tenantsNotRent = tenantRepository.findNotRent();
        for (Tenant tenant : tenantsNotRent) {
            if (tenant.satisfy(house)) {
                // 短信通知顾客
                messageService.sendMessage(tenant.getBasicInfo(TenantInfoType.PHONE), "已有合适房屋供您选择，详情进店咨询");
            }
        }
        // 保存房屋信息
        houseRepository.save(house);
    }
}
