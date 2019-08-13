package lewiszlw.dubbo.demo.client.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-25
 */
@Configuration
@EnableDubbo(scanBasePackages = "lewiszlw.dubbo.demo.client.controller")
@PropertySource("classpath:/dubbo/dubbo-consumer.properties")
@ComponentScan(value = {"lewiszlw.dubbo.demo.client.controller"})
public class ConsumerConfiguration {
}
