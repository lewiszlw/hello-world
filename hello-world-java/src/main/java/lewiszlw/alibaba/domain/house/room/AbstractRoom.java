package lewiszlw.alibaba.domain.house.room;

import lewiszlw.alibaba.domain.house.constant.RoomInfoType;

import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-30
 */
public abstract class AbstractRoom {

    /**
     * 获取房间信息
     */
    public abstract Map<RoomInfoType, String> infos();
}
