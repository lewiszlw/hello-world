package com.github.lewiszlw.springclouddemo.producerservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProducerService2Application {

    public static void main(String[] args) {
        SpringApplication.run(ProducerService2Application.class, args);
    }
}
