package com.github.lewiszlw.grpcclient;

import com.github.lewiszlw.grpcinterface.greeter.GreeterServiceGrpc;
import com.github.lewiszlw.grpcinterface.greeter.HelloRequest;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiController {

    @GrpcClient("greeterServiceStub")
    private GreeterServiceGrpc.GreeterServiceBlockingStub greeterServiceStub;

    @GetMapping("/greeting")
    public String greeting(@RequestParam String name) {
        try {
            return greeterServiceStub.sayHello(HelloRequest.newBuilder().setName(name).build()).getMessage();
        } catch (StatusRuntimeException e) {
            log.error("call greeterServiceStub.sayHello failed due to {}", e.getMessage(), e);
            return "error page";
        }
    }
}
