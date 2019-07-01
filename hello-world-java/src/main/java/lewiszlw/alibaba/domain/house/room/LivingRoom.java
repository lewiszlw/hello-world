package lewiszlw.alibaba.domain.house.room;

import lewiszlw.alibaba.domain.house.constant.RoomInfoType;

import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-30
 */
public class LivingRoom extends AbstractRoom {

    private Map<RoomInfoType, String> infos;

    @Override
    public Map<RoomInfoType, String> infos() {
        return infos;
    }


    /** getter & setter */
    public Map<RoomInfoType, String> getInfos() {
        return infos;
    }

    public void setInfos(Map<RoomInfoType, String> infos) {
        this.infos = infos;
    }
}
