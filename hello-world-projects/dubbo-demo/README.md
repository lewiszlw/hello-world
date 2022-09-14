# dubbo-demo
学习 Dubbo 使用。

## 开始
1. `docker compose up -d`启动zk服务
2. 启动dubbo provider DubboDemoServerApplication
3. 启动dubbo consumer DubboDemoClientApplication
4. 浏览器访问 http://localhost:8081/hello?name=Tom

开启nacos server
1. 下载并解压 nacos-server-2.1.1.tar.gz https://github.com/alibaba/nacos/releases/tag/2.1.1
2. 进入 nacos/bin，执行 `sh startup.sh -m standalone`
3. 浏览器访问 http://127.0.0.1:8848/nacos，用户名/密码为 nacos/nacos