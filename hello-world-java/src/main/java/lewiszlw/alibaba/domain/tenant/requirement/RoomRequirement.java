package lewiszlw.alibaba.domain.tenant.requirement;

import lewiszlw.alibaba.domain.house.House;
import lewiszlw.alibaba.domain.house.constant.RoomType;

/**
 * Desc: 房型要求
 *
 * @author zhanglinwei02
 * @date 2019-06-30
 */
public class RoomRequirement extends AbstractRequirement {

    /** 卧室最少数量 */
    private Integer bedRoomCountMin;

    /** 卧室最多数量 */
    private Integer bedRoomCountMax;

    /** 客厅最少数量 */
    private Integer livingRoomCountMin;

    /** 客厅最大数量 */
    private Integer livingRoomCountMax;

    @Override
    public boolean satisfy(House house) {
        Integer bedRoomCount = house.countRooms(RoomType.Bedroom);
        Integer livingRoomCount = house.countRooms(RoomType.LivingRoom);
        return bedRoomCount >= bedRoomCountMin
                && bedRoomCount <= bedRoomCountMax
                && livingRoomCount >= livingRoomCountMin
                && livingRoomCount <= livingRoomCountMax;
    }
}
