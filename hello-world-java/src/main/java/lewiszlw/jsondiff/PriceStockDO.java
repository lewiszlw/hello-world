package lewiszlw.jsondiff;

import lombok.Data;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/11/1
 * Time:14:56
 */
@Data
public class PriceStockDO {
    private String date;

    private Double marketPrice;

    private Double mtPrice;

    private Double settlementPrice;

    private Double ratio;

    private Integer stock;

    private Integer channelStockType;

    private Integer stockLock;
}
