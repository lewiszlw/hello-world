package com.github.lewiszlw.grpcclient;

import com.github.lewiszlw.grpcinterface.greeter.GreeterServiceGrpc;
import com.github.lewiszlw.grpcinterface.greeter.HelloRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GrpcClient("greeterServiceStub")
    private GreeterServiceGrpc.GreeterServiceBlockingStub greeterServiceStub;

    @GetMapping("/greeting")
    public String greeting(@RequestParam String name) {
        return greeterServiceStub.sayHello(HelloRequest.newBuilder().setName(name).build()).getMessage();
    }
}
