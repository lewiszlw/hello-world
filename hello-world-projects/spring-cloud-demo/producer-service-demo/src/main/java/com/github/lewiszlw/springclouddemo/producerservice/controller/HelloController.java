package com.github.lewiszlw.springclouddemo.producerservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@Slf4j
public class HelloController {

    @Value("${consumer.hello}")
    private String consumerHelloConfig;

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        log.info("producer instance1 handling hello call");
        return "hello " + name;
    }

    @RequestMapping("/test-config")
    public String testConfig() {
        return consumerHelloConfig;
    }
}
