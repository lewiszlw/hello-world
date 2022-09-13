package lewiszlw.dubbo.demo.client;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo  // 必须开启
public class DubboDemoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoClientApplication.class, args);
    }

}
