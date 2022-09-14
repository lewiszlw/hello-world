package lewiszlw.dubbo.demo.nacos.server;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo  // 必须开启
//@NacosConfigurationProperties(dataId = "dubbo-demo-nacos-server.properties", autoRefreshed = true)  // 加载nacos配置
public class DubboDemoNacosServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoNacosServerApplication.class, args);
    }

}
