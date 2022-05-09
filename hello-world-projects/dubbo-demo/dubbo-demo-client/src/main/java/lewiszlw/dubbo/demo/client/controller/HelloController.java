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

    @Reference(version = "1.0.0", group = "impl")
    private HelloService implHelloService;

    @Reference(version = "1.0.0", group = "impl2")
    private HelloService impl2HelloService;

    @RequestMapping("/hello")
    public HelloResponseDTO hello(@RequestParam("name") String name) {
        return implHelloService.hello(new HelloRequestDTO().setName(name).setWords("Hello, I am " + name));
    }
    @RequestMapping("/hello2")
    public HelloResponseDTO hello2(@RequestParam("name") String name) {
        return impl2HelloService.hello(new HelloRequestDTO().setName(name).setWords("Hello, I am " + name));
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
