package com.github.lewiszlw.springclouddemo.consumerservice.controller;

import com.github.lewiszlw.springclouddemo.consumerservice.remote.HelloRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@Slf4j
public class ApiController {

    @Autowired
    private HelloRemote helloRemote;

    @Value("${consumer.hello}")
    private String consumerHelloConfig;

    @RequestMapping("/test-call")
    public String testCall() {
        log.info("consumer call producer hello api");
        return helloRemote.hello("lewis");
    }

    @RequestMapping("/test-config")
    public String testConfig() {
        return consumerHelloConfig;
    }
}
