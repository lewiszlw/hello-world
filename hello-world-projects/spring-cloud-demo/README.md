# Spring Cloud Demo
> Spring Cloud是一系列框架的有序集合。它利用Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发，
> 如服务发现注册、配置中心、消息总线、负载均衡、断路器、数据监控等，都可以用Spring Boot的开发风格做到一键启动和部署。
> Spring并没有重复制造轮子，它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组合起来，
> 通过Spring Boot风格进行再封装屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂、易部署和易维护的分布式系统开发工具包。

此项目主要学习 
- 注册中心 eureka
- 服务间调用 openfeign
- 熔断降级 hystrix
- 动态配置中心 spring cloud config
- 消息总线 spring cloud bus
- 服务网关 spring cloud gateway
- 链路追踪 spring cloud sleuth + zipkin

## eureka-server-demo
服务中心 / 注册中心，负责服务注册和发现等功能。

使用 @EnableEurekaServer 即可开启注册中心。

本地启动 EurekaServerApplication 服务后，可访问 http://localhost:8000/ 来查看注册中心状态。

## config-server-demo
> Spring Cloud Config项目是一个解决分布式系统的配置管理方案。它包含了Client和Server两个部分，
> server提供配置文件的存储、以接口的形式将配置文件的内容提供出去，client通过接口获取数据、并依据此数据初始化自己的应用。
> Spring cloud使用git或svn存放配置文件，默认情况下使用git。

使用 @EnableConfigServer 即可开启动态配置中心。同时使用 @EnableDiscoveryClient 将自己注册到服务中心 Eureka 来实现服务化，
提高可用性。需要在 application.properties 中配置 git 仓库地址（本项目采用 github）。

仓库中的配置文件会被转换成web接口，访问可以参照以下的规则：
```
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
```
本地启动 ConfigServerApplication 服务后，可访问 http://localhost:8001/consumer-service-demo/prod 来查看配置。

客户端获取配置时，需要配置 bootstrap.properties 文件，包括 spring cloud config 服务发现配置以及 git 仓库文件配置。

客户端（如 ConsumerServiceApplication）服务启动后可直接用 `@Value` 注解来读取配置中心的配置。
如需要动态刷新配置，需要引入 spring-boot-starter-actuator 包，同时在 application.properties 中开启 refresh API，
利用 `@RefreshScope` 注解来实现刷新。例如执行 `curl -X POST http://localhost:9001/actuator/refresh` 来刷新配置。

利用 Spring Cloud Bus 可以实现一次请求各个服务配置自动刷新
> Spring cloud bus通过轻量消息代理连接各个分布的节点，可用在广播状态的变化（例如配置变化）或者其他的消息指令。

1. 提交代码触发post请求（git仓库webhook）到 actuator/busrefresh API
2. server端接收到请求并发送给Spring Cloud Bus
3. Spring Cloud Bus接到消息并通知给其它客户端
4. 其它客户端接收到通知，请求Server端获取最新配置
5. 全部客户端均获取到最新的配置

引入 spring-cloud-starter-bus-amqp 以及 spring-boot-starter-actuator 包，在 application.properties 
中配置 rabbitmq 地址并开启 busrefresh API， 即可接入消息总线。

启动 ConfigServerApplication, ConsumerServiceApplication 和 ProducerServiceApplication 服务后，
- 执行`curl -X POST http://localhost:8001/actuator/busrefresh` 来刷新所有客户端配置。
- 执行`curl -X POST http://localhost:8001/actuator/busrefresh/consumer-service-demo:9001` 来进行局部刷新。


启动 rabbitmq docker 容器

`docker run -d --hostname rabbit-host1 --name rabbitmq -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=123456 -p 15672:15672 -p 5672:5672 -p 25672:25672 -p 61613:61613 -p 1883:1883 rabbitmq:management`

可通过 http://localhost:15672/ 访问 rabbitmq 控制台。

## producer-service-demo 和 producer-service-demo2
producer-service-demo 和 producer-service-demo2 模仿同一服务的两个节点，其 /hello API 返回不同的值。

## consumer-service-demo
利用 openfeign 来进行远程调用，同时可以在 feign 中配置熔断降级，需在 application.properties 中开启熔断。

本地启动 ConsumerServiceApplication 服务后，访问 http://localhost:9001/test-call 来进行服务间调用，
访问 http://localhost:9001/test-config 来查看动态配置读取。

关闭 ProducerServiceApplication 服务后，访问 http://localhost:9001/test-call 可看到熔断器返回结果。

## gateway-eureka-demo
> Spring Cloud Gateway 是 Spring Cloud 的一个全新项目，该项目是基于 Spring 5.0，Spring Boot 2.0 和
> Project Reactor 等技术开发的网关， 它旨在为微服务架构提供一种简单有效的统一的 API 路由管理方式。

> Route（路由）：这是网关的基本构建块。它由一个 ID，一个目标 URI，一组断言和一组过滤器定义。如果断言为真，则路由匹配。

> Predicate（断言）：这是一个 Java 8 的 Predicate。输入类型是一个 ServerWebExchange。我们可以使用它来匹配来自 HTTP 请求的任何内容，例如 headers 或参数。

> Filter（过滤器）：这是org.springframework.cloud.gateway.filter.GatewayFilter的实例，我们可以使用它修改请求和响应。

Spring Cloud Gateway 网关路由有两种配置方式：
- 在配置文件 yml 中配置
- 通过 `@Bean` 自定义 RouteLocator，在启动主类 Application 中配置

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: producer_hello_route
          uri: http://localhost:9002
          predicates:
            - Path=/hello
          filters:
            - AddRequestParameter=name, xxx
```
- id：我们自定义的路由 ID，保持唯一
- uri：目标服务地址
- predicates：路由条件，Predicate 接受一个输入参数，返回一个布尔值结果。该接口包含多种默认方法来将 Predicate 组合成其他复杂的逻辑（比如：与，或，非）
- filters：过滤规则

因此当访问地址 http://localhost:8002/hello 时会自动转发到地址：http://localhost:9002/hello?name=xxx 。

本地启动 GatewayEurekaApplication 服务后，
- 访问 http://localhost:8002/lewiszlw 将转到 https://github.com/lewiszlw
- 访问 http://localhost:8002/vczh 将转到 https://github.com/vczh
- 访问 http://localhost:8002/hello 将转到 http://localhost:9002/hello?name=xxx

将 Spring Cloud Gateway 注册到服务中心之后，网关会自动代理所有的在注册中心的服务，访问这些服务的语法为：`http://{gateway ip}:{gateway port}/{serviceId}/{service path}`
- 访问 http://localhost:8002/CONSUMER-SERVICE-DEMO/test-call 会转到 http://localhost:9001/test-call
- 访问 http://localhost:8002/PRODUCER-SERVICE-DEMO/test-config 会转到 http://localhost:9000/test-config

多次访问 http://localhost:8002/PRODUCER-SERVICE-DEMO/hello?name=lewis ，页面交替返回以下信息
```
hello lewis
或
hello2 lewis
```

## 链路追踪
> 服务追踪的追踪单元是从客户发起请求（request）抵达被追踪系统的边界开始，到被追踪系统向客户返回响应（response）为止的过程，
> 称为一个“trace”。每个 trace 中会调用若干个服务，为了记录调用了哪些服务，以及每次调用的消耗时间等信息，在每次调用服务时，
> 埋入一个调用记录，称为一个“span”。这样，若干个有序的 span 就组成了一个 trace。在系统向外界提供服务的过程中，
> 会不断地有请求和响应发生，也就会不断生成 trace，把这些带有span 的 trace 记录下来，就可以描绘出一幅系统的服务拓扑图。
> 附带上 span 中的响应时间，以及请求成功与否等信息，就可以在发生问题的时候，找到异常的服务；根据历史数据，
> 还可以从系统整体层面分析出哪里性能差，定位性能优化的目标。

启动 zipkin server: `docker run --name zipkin -d -p 9411:9411 openzipkin/zipkin`，
访问 http://localhost:9411/zipkin/ 可查看链路信息。

各个服务（包括网关）引入 `spring-cloud-starter-sleuth` 和 `spring-cloud-sleuth-zipkin` 包后，在 application.properties 
配置文件中配置 zipkin server 地址，Spring应用在监测到Java依赖包中有sleuth和zipkin后， 会自动在RestTemplate的
调用过程中向HTTP请求注入追踪信息，并向Zipkin Server发送这些信息。此时服务日志格式如下，
```
2021-11-02 12:29:19.808  INFO [consumer-service-demo,4b27f765a3012fc9,4b27f765a3012fc9] 18228 --- [nio-9001-exec-4] c.g.l.s.c.controller.ApiController       : consumer call producer hello api
2021-11-02 12:29:20.795  INFO [producer-service-demo,1443733d81d68d0f,944a81d3c6b5a169] 18315 --- [io-9000-exec-10] c.g.l.s.p.controller.HelloController     : producer instance1 handling hello call
2021-11-02 12:29:21.069  INFO [producer-service-demo,4b27f765a3012fc9,04195e2926d83164] 18328 --- [nio-9002-exec-1] c.g.l.s.p.controller.HelloController     : producer instance2 handling hello call
```
格式为 `[{serviceName},{traceId},{spanId}]`。

此时可以在 http://localhost:9411/zipkin/ 可搜索查看链路信息。

# Reference
https://github.com/ityouknow/spring-cloud-examples

https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/

https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/

https://docs.spring.io/spring-cloud-config/docs/current/reference/html/

https://docs.spring.io/spring-cloud-bus/docs/current/reference/html/

https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/