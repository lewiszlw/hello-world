# nginx

## web server
1. 编写静态 html 在 nginx/html（会挂载到 nginx 容器 /usr/share/nginx/html）
2. 配置 nginx/nginx.conf（会挂载到 nginx 容器 /etc/nginx/nginx.conf）
3. 修改宿主机 /etc/hosts，配置域名
```yaml
127.0.0.1	localhost nginx-demo.com
```
4. 启动容器 `docker compose up -d`
5. 访问 http://nginx-demo.com 可看到页面

## load balancer