package lewiszlw.dubbo.demo.client.controller;

import lewiszlw.dubbo.demo.iface.HelloRequestDTO;
import lewiszlw.dubbo.demo.iface.HelloResponseDTO;
import lewiszlw.dubbo.demo.iface.HelloService;
import org.apache.dubbo.config.annotation.Reference;
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
public class HelloController {

    @Reference(version = "1.0.0")
    private HelloService helloService;

    @RequestMapping("/hello")
    public HelloResponseDTO hello(@RequestParam("name") String name) {
        return helloService.hello(new HelloRequestDTO().setName(name).setWords("Hello, I am " + name));
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
