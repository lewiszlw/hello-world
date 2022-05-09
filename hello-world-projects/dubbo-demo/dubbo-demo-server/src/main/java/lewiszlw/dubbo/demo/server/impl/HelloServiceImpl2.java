package lewiszlw.dubbo.demo.server.impl;

import lewiszlw.dubbo.demo.iface.HelloRequestDTO;
import lewiszlw.dubbo.demo.iface.HelloResponseDTO;
import lewiszlw.dubbo.demo.iface.HelloService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceImpl2 implements HelloService {
    public HelloResponseDTO hello(HelloRequestDTO requestDTO) {
        return new HelloResponseDTO()
                .setName("server")
                .setResp("Hi, " + requestDTO.getName() + ". I am server(impl2)");
    }
}
