# 启动
1.安装rocketmq

   http://rocketmq.apache.org/docs/quick-start/
   
2.启动rocketmq
```bash
nohup sh bin/mqnamesrv &
nohup sh bin/mqbroker -n localhost:9876 &
```
3.启动项目

运行RocketmqDemoApplication

发送Http GET请求 http://localhost:8080/send?body=hello

控制台输出消费者日志

4.关闭

关闭web应用

关闭rocketmq
```bash
sh bin/mqshutdown broker
sh bin/mqshutdown namesrv
```
