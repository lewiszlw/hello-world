package lewiszlw.dubbo.demo.server;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo  // 必须开启
public class DubboDemoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoServerApplication.class, args);
    }

}
