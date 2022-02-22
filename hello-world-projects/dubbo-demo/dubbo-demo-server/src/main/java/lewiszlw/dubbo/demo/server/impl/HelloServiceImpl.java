package lewiszlw.dubbo.demo.server.impl;

import lewiszlw.dubbo.demo.iface.HelloRequestDTO;
import lewiszlw.dubbo.demo.iface.HelloResponseDTO;
import lewiszlw.dubbo.demo.iface.HelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-25
 */
@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    public HelloResponseDTO hello(HelloRequestDTO requestDTO) {
        return new HelloResponseDTO()
                .setName("server")
                .setResp("Hi, " + requestDTO.getName() + ". I am server");
    }
}
