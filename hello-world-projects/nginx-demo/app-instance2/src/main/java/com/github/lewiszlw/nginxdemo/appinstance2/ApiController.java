package com.github.lewiszlw.nginxdemo.appinstance2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/app")
public class ApiController {

    @GetMapping("/uuid")
    public String uuid() {
        return UUID.randomUUID() + " (instance2)";
    }
}
