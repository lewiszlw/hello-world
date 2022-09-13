package lewiszlw.dubbo.demo.server.config;

import lewiszlw.dubbo.demo.server.impl.GreeterServiceImpl2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GreeterServiceImpl2Configuration {

    @Conditional(GreeterServiceImpl2Condition.class)
    @Bean
    public GreeterServiceImpl2 greeterServiceImpl2() {
        GreeterServiceImpl2 greeterService = new GreeterServiceImpl2();
        log.info("TEST ====== GreeterService impl2 bean has been inited.");
        return greeterService;
    }
}
