package lewiszlw.jsondiff;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/11/1
 * Time:14:55
 */
@Data
public class MtpChannelDO {
    private Integer id;

    private Integer mtpDealId;

    private Integer mtChannelId;

    private Integer channelType;

    private String channelName;

    private String distributor;

    private Integer autoSubmit;

    private Integer scheduleOnlineType;

    private Date scheduleOnlineTime;

    private Integer scheduleOfflineType;

    private Date scheduleOfflineTime;

    private Integer onlineStatus;

    private Integer ratioCheckStatus;

    private Integer stockType;

    private Integer priceLockType;

    private Integer useMode;

    private List<PriceStockDO> priceStocks;

    private Integer isDeleted;

    private Date createdTime;

    private Date updatedTime;
}
