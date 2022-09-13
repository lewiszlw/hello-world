package lewiszlw.dubbo.demo.server.config;

import lewiszlw.dubbo.demo.server.impl.GreeterServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GreeterServiceImplConfiguration {

    @Conditional(GreeterServiceImplCondition.class)
    @Bean
    public GreeterServiceImpl greeterServiceImpl() {
        GreeterServiceImpl greeterService = new GreeterServiceImpl();
        log.info("TEST ====== GreeterService impl bean has been inited.");
        return greeterService;
    }
}
