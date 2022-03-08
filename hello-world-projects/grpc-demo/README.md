# grpc-demo
- 服务间调用
- 异常处理

注册发现：

可以通过 net.devh:grpc-spring-boot-starter 自带的支持如 eureka （可参考 spring-cloud-demo 中 eureka-server-demo 模块实现）
来实现服务注册发现；也可以通过 agent 方式（需自行开发），每个 client 或 server 上附带一个 agent 来管理服务注册和发现。

## 接口定义 grpc-interface
1. 创建 .proto 服务定义文件在 /src/main/proto 目录下
2. 引入 grpc 依赖
3. 配置 protobuf 插件
4. 执行 Gradle Task generateProto 生成 Java 类在 build 目录（自动关联到 classpath）

## 服务端 grpc-server
1. 引入 grpc 依赖 和 grpc-interface
2. 实现 grpc-interface xxxGrpc.xxxImplBase 类

## 客户端 grpc-client
1. 引入 grpc 依赖 和 grpc-interface
2. 使用 grpc-interface xxxGrpc.xxxStub 类来进行远程调用
3. 对 grpc client 配置网络地址（注意 grpc-server http 端口和 grpc 端口不一致）

# 常见问题
**1.invalid source release: 17**

本项目使用 JDK 17，需要将 Gradle 配置成 JDK 17 (Preferences -> Build, Execution, Deployment -> Build Tools -> Gradle -> Gradle JVM)。