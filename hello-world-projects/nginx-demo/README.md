# nginx-demo

## web server
1. 编写静态 html 在 nginx/html（会挂载到 nginx 容器 /usr/share/nginx/html）
2. 配置 nginx/nginx.conf（会挂载到 nginx 容器 /etc/nginx/nginx.conf）
3. 修改宿主机 /etc/hosts，配置域名
```yaml
127.0.0.1	localhost nginx-demo.com
```
4. 启动容器 `docker compose up -d`
5. 访问 http://nginx-demo.com 可看到页面

## load balancer & reverse proxy
1. 配置 nginx/nginx.conf（注意 127.0.0.1 使用 host.docker.internal 替代），将请求路由到后端节点执行
2. 启动容器 `docker compose up -d`
3. 访问 http://nginx-demo.com/app/uuid 可看到页面，不断刷新页面可看到请求按负载比例发到后端节点

反向代理（Reverse Proxy）方式是指以代理服务器来接受 internet 上的连接请求，然后将请求转发给内部网络上的服务器，并将从服务器上得到的结果返回给 internet 上请求连接的客户端，此时代理服务器对外就表现为一个反向代理服务器。