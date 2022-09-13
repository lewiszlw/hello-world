package lewiszlw.dubbo.demo.server.impl;

import lewiszlw.dubbo.demo.iface.GreeterService;
import lewiszlw.dubbo.demo.iface.HelloRequestDTO;
import lewiszlw.dubbo.demo.iface.HelloResponseDTO;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class GreeterServiceImpl implements GreeterService {
    public HelloResponseDTO hello(HelloRequestDTO requestDTO) {
        return new HelloResponseDTO()
                .setName("server")
                .setResp("Hi, " + requestDTO.getName() + ". I am server (by GreeterServiceImpl)");
    }
}
