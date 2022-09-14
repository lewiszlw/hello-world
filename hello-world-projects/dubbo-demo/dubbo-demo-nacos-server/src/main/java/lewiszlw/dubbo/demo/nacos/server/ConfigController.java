package lewiszlw.dubbo.demo.nacos.server;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConfigController {

//    @NacosValue(value = "${experiment.scene.ids}", autoRefreshed = true)
    @NacosValue(value = "${password:123}", autoRefreshed = true)
    private String password;

    @GetMapping("/configValue")
    public String getConfigValue() {
        if (password == null) {
            log.warn("password is null");
        }
        return password;
    }
}
