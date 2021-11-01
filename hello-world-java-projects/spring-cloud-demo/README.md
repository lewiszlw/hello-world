# Spring Cloud Demo
学习 spring-cloud eureka, openfeign, hystrix, config, bus, gateway, etc。

> Spring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，
> 如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署。
> Spring并没有重复制造轮子，它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，
> 通过Spring Boot风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包。

## eureka-server-demo
服务中心/注册中心，负责服务注册和发现等功能。

本地启动服务后，可访问 http://localhost:8000/ 来查看注册中心状态。

## config-server-demo
> Spring Cloud Config项目是一个解决分布式系统的配置管理方案。它包含了Client和Server两个部分，
> server提供配置文件的存储、以接口的形式将配置文件的内容提供出去，client通过接口获取数据、并依据此数据初始化自己的应用。
> Spring cloud使用git或svn存放配置文件，默认情况下使用git。

> Spring cloud bus通过轻量消息代理连接各个分布的节点，可用在广播状态的变化（例如配置变化）或者其他的消息指令。

利用 Spring Cloud Bus 可以实现配置的自动刷新
1. 提交代码触发post请求（git仓库webhook）到 actuator/busrefresh API
2. server端接收到请求并发送给Spring Cloud Bus
3. Spring Cloud Bus接到消息并通知给其它客户端
4. 其它客户端接收到通知，请求Server端获取最新配置
5. 全部客户端均获取到最新的配置

本地启动服务后，可访问 http://localhost:8001/consumer-service-demo/prod 来查看配置。

启动 rabbitmq docker 容器
`docker run -d --hostname rabbit-host1 --name rabbitmq -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=123456 -p 15672:15672 -p 5672:5672 -p 25672:25672 -p 61613:61613 -p 1883:1883 rabbitmq:management`
，可通过 http://localhost:15672/ 访问 rabbitmq 控制台。

执行`curl -X POST http://localhost:8001/actuator/busrefresh` 来刷新所有客户端配置。
执行`curl -X POST http://localhost:8001/actuator/busrefresh/consumer-service-demo:9001` 来进行局部刷新。

## producer-service-demo 和 producer-service-demo2
producer-service-demo 和 producer-service-demo2 模仿同一服务的两个节点，其 /hello API 返回不同的值。

## consumer-service-demo
利用 openfeign 来进行远程调用，同时可以在 feign 中配置熔断降级。

本地启动服务后，可执行 `curl -X POST http://localhost:9001/actuator/refresh` 来单独刷新配置。

## gateway-eureka-demo
> Spring Cloud Gateway 是 Spring Cloud 的一个全新项目，该项目是基于 Spring 5.0，Spring Boot 2.0 和
> Project Reactor 等技术开发的网关， 它旨在为微服务架构提供一种简单有效的统一的 API 路由管理方式。

可以通过 yml 或者 bean 两种方式进行路由配置。

本地启动服务后，
访问 http://localhost:8002/lewiszlw 将转到 https://github.com/lewiszlw
访问 http://localhost:8002/vczh 将转到 https://github.com/vczh
访问 http://localhost:8002/hello 将转到 http://localhost:9002/hello?name=xxx

将 Spring Cloud Gateway 注册到服务中心之后，网关会自动代理所有的在注册中心的服务，访问这些服务的语法为：`http://{gateway ip}:{gateway port}/serviceId/{service url}`
如访问 http://localhost:8002/CONSUMER-SERVICE-DEMO/test-call 会转到 http://localhost:9001/test-call
如访问 http://localhost:8002/PRODUCER-SERVICE-DEMO/test-config 会转到 http://localhost:9000/test-config

多次访问 http://localhost:8002/PRODUCER-SERVICE-DEMO/hello?name=lewis，页面交替返回以下信息
```
hello lewis
或
hello2 lewis
```

# Reference
https://github.com/ityouknow/spring-cloud-examples
https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/
https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/
https://docs.spring.io/spring-cloud-config/docs/current/reference/html/
https://docs.spring.io/spring-cloud-bus/docs/current/reference/html/
https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/