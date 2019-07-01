package lewiszlw.alibaba.application;

import lewiszlw.alibaba.domain.house.House;
import lewiszlw.alibaba.domain.house.HouseRepository;
import lewiszlw.alibaba.domain.tenant.Tenant;
import lewiszlw.alibaba.domain.tenant.TenantRepository;
import lewiszlw.alibaba.domain.tenant.constant.TenantInfoType;
import lewiszlw.alibaba.infrastructure.message.MessageService;
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
public class RentingApplicationService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private MessageService messageService;

    /**
     * 登记租房顾客信息
     */
    public void registerTenant(Tenant tenant) {
        // 判断现有房子是否满足条件
        List<House> housesforRent = houseRepository.findForRent();
        for (House house: housesforRent) {
            if (tenant.satisfy(house)) {
                // 短信通知顾客
                messageService.sendMessage(tenant.getBasicInfo(TenantInfoType.PHONE), "已有合适房屋供您选择，详情进店咨询");
                break;
            }
        }
        // 登记顾客信息
        tenantRepository.register(tenant);
    }



}
