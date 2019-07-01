package lewiszlw.alibaba.infrastructure.persist;

import lewiszlw.alibaba.domain.house.House;
import lewiszlw.alibaba.domain.house.HouseRepository;

import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
public class HouseRepositoryImpl implements HouseRepository {
    @Override
    public List<House> findForRent() {
        // 查找DB，将DO模型转换为领域模型
        return null;
    }

    @Override
    public void save(House house) {
        // 将领域模型转换为DO模型存储到DB
    }
}
