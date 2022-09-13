package lewiszlw.dubbo.demo.server.impl;

import lewiszlw.dubbo.demo.iface.GreeterService;
import lewiszlw.dubbo.demo.iface.HelloRequestDTO;
import lewiszlw.dubbo.demo.iface.HelloResponseDTO;

// 对比GreeterServiceImpl，@DubboService 会进行初始化bean（即使Condition设置为false）
public class GreeterServiceImpl2 implements GreeterService {
    public HelloResponseDTO hello(HelloRequestDTO requestDTO) {
        return new HelloResponseDTO()
                .setName("server")
                .setResp("Hi, " + requestDTO.getName() + ". I am server (by GreeterServiceImpl2)");
    }
}
