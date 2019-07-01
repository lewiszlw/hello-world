package lewiszlw.alibaba.domain.tenant.requirement;

import lewiszlw.alibaba.domain.house.House;

/**
 * Desc: 租金要求
 *
 * @author zhanglinwei02
 * @date 2019-06-30
 */
public class RentRequirement extends AbstractRequirement {

    /** 租金最小值 */
    private Long min;

    /** 租金最大值 */
    private Long max;

    @Override
    public boolean satisfy(House house) {
        Long amount = house.getRentAmount();
        return amount >= min && amount <= max;
    }
}
