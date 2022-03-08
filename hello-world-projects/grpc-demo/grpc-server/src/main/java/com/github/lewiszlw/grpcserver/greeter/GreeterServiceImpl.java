package com.github.lewiszlw.grpcserver.greeter;

import com.github.lewiszlw.grpcinterface.greeter.GreeterServiceGrpc;
import com.github.lewiszlw.grpcinterface.greeter.HelloRequest;
import com.github.lewiszlw.grpcinterface.greeter.HelloResponse;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.util.StringUtils;

/**
 * Greeter服务
 * 注意：@GrpcService上已加有@Service，可直接引用spring bean
 */
@GrpcService
@Slf4j
public class GreeterServiceImpl extends GreeterServiceGrpc.GreeterServiceImplBase {
    @Override
    public void sayHello(HelloRequest request, io.grpc.stub.StreamObserver<HelloResponse> responseObserver) {
        if (!StringUtils.hasText(request.getName())) {
            responseObserver.onError(
                    Status.INVALID_ARGUMENT.withDescription("name is empty").asRuntimeException()
            );
            return;
        }
        HelloResponse helloResponse = HelloResponse.newBuilder()
                .setMessage("Hello, " + request.getName()).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }
}
