package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example.demoapp", "com.example.demobiz", "com.example.demodal"})  // 将其他模块bean纳入管理
public class DemoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAppApplication.class, args);
    }
}
