package lewiszlw.alibaba.domain.house;

import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
public interface HouseRepository {

    /**
     * 查找待租房子
     */
    List<House> findForRent();

    /**
     * 保存房屋信息
     */
    void save(House house);
}
