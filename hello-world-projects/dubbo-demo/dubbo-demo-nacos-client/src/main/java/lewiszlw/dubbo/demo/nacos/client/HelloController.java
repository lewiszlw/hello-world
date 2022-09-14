package lewiszlw.dubbo.demo.nacos.client;

import lewiszlw.dubbo.demo.iface.HelloRequestDTO;
import lewiszlw.dubbo.demo.iface.HelloResponseDTO;
import lewiszlw.dubbo.demo.iface.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @DubboReference
    private HelloService helloService;

    @GetMapping("/hello")
    public HelloResponseDTO hello(@RequestParam("name") String name) {
        return helloService.hello(new HelloRequestDTO().setName(name).setWords("Hello, I am " + name));
    }
}
