package lewiszlw.dubbo.demo.server.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-25
 */
@Configuration
@EnableDubbo(scanBasePackages = "lewiszlw.dubbo.demo.server.impl")
@PropertySource("classpath:/dubbo/dubbo-provider.properties")
public class ProviderConfiguration {

}
