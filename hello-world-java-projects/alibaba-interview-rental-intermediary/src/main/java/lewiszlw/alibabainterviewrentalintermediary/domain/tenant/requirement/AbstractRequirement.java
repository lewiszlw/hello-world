package lewiszlw.alibabainterviewrentalintermediary.domain.tenant.requirement;

import lewiszlw.alibabainterviewrentalintermediary.domain.house.House;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-30
 */
public abstract class AbstractRequirement {

    public abstract boolean satisfy(House house);
}
