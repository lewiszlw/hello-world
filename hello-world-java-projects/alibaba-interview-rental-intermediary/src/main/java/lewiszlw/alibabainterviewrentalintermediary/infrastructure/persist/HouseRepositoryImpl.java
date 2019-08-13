package lewiszlw.alibabainterviewrentalintermediary.infrastructure.persist;

import lewiszlw.alibabainterviewrentalintermediary.domain.house.House;
import lewiszlw.alibabainterviewrentalintermediary.domain.house.HouseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-07-01
 */
@Component
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
