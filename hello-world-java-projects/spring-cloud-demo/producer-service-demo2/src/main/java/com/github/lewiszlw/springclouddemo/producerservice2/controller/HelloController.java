package com.github.lewiszlw.springclouddemo.producerservice2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${consumer.hello}")
    private String consumerHelloConfig;

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hello2 " + name;
    }

    @RequestMapping("/test-config")
    public String testConfig() {
        return consumerHelloConfig;
    }
}