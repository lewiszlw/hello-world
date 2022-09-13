package lewiszlw.dubbo.demo.server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @RequestMapping("/bean")
    public boolean getBean(@RequestParam("name") String name) {
        ApplicationContextUtil.getBean(name);  // 要么成功，要么抛出异常
        return true;
    }
}
