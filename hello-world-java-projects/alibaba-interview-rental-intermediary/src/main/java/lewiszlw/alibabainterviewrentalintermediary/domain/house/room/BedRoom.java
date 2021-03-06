package lewiszlw.alibabainterviewrentalintermediary.domain.house.room;

import lewiszlw.alibabainterviewrentalintermediary.domain.house.constant.RoomInfoType;

import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-30
 */
public class BedRoom extends AbstractRoom {

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
