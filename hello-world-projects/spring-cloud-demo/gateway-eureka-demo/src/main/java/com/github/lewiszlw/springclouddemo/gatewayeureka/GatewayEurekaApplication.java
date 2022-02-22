package com.github.lewiszlw.springclouddemo.gatewayeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayEurekaApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 会转到 https://github.com/vczh
                .route("github_route3", r -> r.path("/vczh").uri("https://github.com/spring-cloud"))
                .build();
    }
}
