# Spring Cloud Demo

## config-server-demo
本地启动服务后，可访问 http://localhost:8001/consumer-service-demo/prod 来查看配置。

启动 rabbitmq docker 容器
`docker run -d --hostname rabbit-host1 --name rabbitmq -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=123456 -p 15672:15672 -p 5672:5672 -p 25672:25672 -p 61613:61613 -p 1883:1883 rabbitmq:management`
可通过 http://localhost:15672/ 访问 rabbitmq 控制台

## consumer-service-demo
本地启动服务后，可执行 `curl -X POST http://localhost:9001/actuator/refresh` 来刷新配置。
