package lewiszlw.dubbo.demo.server.config;

import lewiszlw.dubbo.demo.iface.HelloService;
import lewiszlw.dubbo.demo.server.ApplicationContextUtil;
import lewiszlw.dubbo.demo.server.impl.HelloServiceImpl2;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("helloServiceImpl2")
public class ProviderConfiguration2 {
    @PostConstruct
    public void exportProviders() {
        // 服务提供者暴露服务配置
        ServiceConfig<HelloServiceImpl2> service = new ServiceConfig<HelloServiceImpl2>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        service.setApplication(new ApplicationConfig("dubbo-demo-server"));
        service.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181")); // 多个注册中心可以用setRegistries()
        service.setProtocol(new ProtocolConfig("dubbo")); // 多个协议可以用setProtocols()
        service.setInterface(HelloService.class);
        service.setRef(ApplicationContextUtil.getBean(HelloServiceImpl2.class));
        service.setGroup("impl2");
        service.setVersion("1.0.0");
        service.export();
    }
}
