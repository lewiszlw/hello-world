package com.github.lewiszlw.springclouddemo.consumerservice.hystrix;

import com.github.lewiszlw.springclouddemo.consumerservice.remote.HelloRemote;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloRemoteFallbackFactory implements FallbackFactory<HelloRemote> {
    @Override
    public HelloRemote create(Throwable cause) {
        return new HelloRemote() {
            @Override
            public String hello(String name) {
                return "hello " + name + " (factory fallback)";
            }
        };
    }
}
