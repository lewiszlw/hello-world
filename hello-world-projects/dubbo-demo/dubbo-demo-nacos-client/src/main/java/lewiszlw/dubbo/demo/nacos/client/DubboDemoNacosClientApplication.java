package lewiszlw.dubbo.demo.nacos.client;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo  // 必须开启
public class DubboDemoNacosClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoNacosClientApplication.class, args);
    }

}
