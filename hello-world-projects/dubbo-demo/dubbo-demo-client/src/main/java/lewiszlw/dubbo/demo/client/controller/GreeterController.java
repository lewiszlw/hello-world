package lewiszlw.dubbo.demo.client.controller;

import lewiszlw.dubbo.demo.iface.GreeterService;
import lewiszlw.dubbo.demo.iface.HelloRequestDTO;
import lewiszlw.dubbo.demo.iface.HelloResponseDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-25
 */
@RestController
public class GreeterController {

    @DubboReference
    private GreeterService greeterService;

    @RequestMapping("/greet")
    public HelloResponseDTO greet(@RequestParam("name") String name) {
        return greeterService.hello(new HelloRequestDTO().setName(name).setWords("Hello, I am " + name));
//        return null;
    }
}
