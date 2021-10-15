package com.github.lewiszlw.springclouddemo.consumerservice.hystrix;

import com.github.lewiszlw.springclouddemo.consumerservice.remote.HelloRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Slf4j
public class HelloRemoteHystrix implements HelloRemote {

    @Override
    public String hello(@RequestParam(value = "name") String name) {
        log.warn("hello fallback");
        return "hello " + name + " (fallback message)";
    }
}
