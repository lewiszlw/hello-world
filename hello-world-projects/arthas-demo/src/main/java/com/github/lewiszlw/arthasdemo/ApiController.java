package com.github.lewiszlw.arthasdemo;

import com.github.lewiszlw.arthasdemo.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@RestController
public class ApiController {

    private static final ScheduledExecutorService EXECUTOR_SERVICE = new ScheduledThreadPoolExecutor(1);

    @Autowired
    private AlphaService alphaService;

    @PostConstruct
    public void init() {
        EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(1900);
                System.out.println("executed task at " + new Date());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 1, 2, TimeUnit.SECONDS);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", required = false) String name) {
        if (name == null) {
            name = "stranger";
        }
        return "hello " + name;
    }

    @GetMapping("/queryNames")
    public List<String> queryNames(@RequestParam(value = "age", required = false) Integer age) throws InterruptedException {
        if (age == null) {
            return Collections.emptyList();
        }
        return alphaService.queryNamesByAge(age);
    }
}
