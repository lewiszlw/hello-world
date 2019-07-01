package lewiszlw.alibaba.domain.house;

import lewiszlw.alibaba.domain.house.constant.RentDetailType;

import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-30
 */
public class Rent {

    /** 租金总额 */
    private Long amount;

    /** 租金细节 */
    private Map<RentDetailType, Long> details;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
