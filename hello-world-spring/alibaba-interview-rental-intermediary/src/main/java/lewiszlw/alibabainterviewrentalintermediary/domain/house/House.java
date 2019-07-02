package lewiszlw.alibabainterviewrentalintermediary.domain.house;

import lewiszlw.alibabainterviewrentalintermediary.domain.house.constant.RoomType;
import lewiszlw.alibabainterviewrentalintermediary.domain.house.room.AbstractRoom;
import lewiszlw.alibabainterviewrentalintermediary.domain.house.room.BedRoom;
import lewiszlw.alibabainterviewrentalintermediary.domain.house.room.LivingRoom;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Desc: 租房领域模型
 *
 * @author zhanglinwei02
 * @date 2019-06-30
 */
public class House {

    /**房子id*/
    Integer houseId;

    /**房间信息*/
    List<AbstractRoom> rooms;

    /**租金信息*/
    Rent rent;

    /**是否租出*/
    boolean rentOut;

    public Integer getHouseId() {
        return houseId;
    }

    public boolean getRentOut() {
        return rentOut;
    }

    /**
     * 获取某类型房间数量
     * roomType传null则统计所有类型房间
     */
    public Integer countRooms(RoomType roomType) {
        if (CollectionUtils.isEmpty(rooms)) {
            return 0;
        }
        Integer count = 0;
        for (AbstractRoom room : rooms) {
            if (roomType == null) {
                count ++;
                continue;
            }
            if (roomType == RoomType.Bedroom && room instanceof BedRoom) {
                count ++;
            }
            if (roomType == RoomType.LivingRoom && room instanceof LivingRoom) {
                count ++;
            }
        }
        return count;
    }


    public Long getRentAmount() {
        if (rent == null) {
            throw new IllegalArgumentException("租金为空");
        }
        return rent.getAmount();
    }

}
