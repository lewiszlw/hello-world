package com.github.lewiszlw.grpcserver.greeter;

import com.github.lewiszlw.grpcinterface.greeter.GreeterServiceGrpc;
import com.github.lewiszlw.grpcinterface.greeter.HelloRequest;
import com.github.lewiszlw.grpcinterface.greeter.HelloResponse;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * Greeter服务
 * 注意：@GrpcService上已加有@Service，可直接引用spring bean
 */
@GrpcService
@Slf4j
public class GreeterServiceImpl extends GreeterServiceGrpc.GreeterServiceImplBase {
    @Override
    public void sayHello(HelloRequest request, io.grpc.stub.StreamObserver<HelloResponse> responseObserver) {
        HelloResponse helloResponse = HelloResponse.newBuilder()
                .setMessage("Hello, " + request.getName()).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }
}
