package com.github.lewiszlw.springclouddemo.consumerservice.controller;

import com.github.lewiszlw.springclouddemo.consumerservice.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping("/test-call")
    public String testCall() {
        return helloRemote.hello("lewis");
    }
}
