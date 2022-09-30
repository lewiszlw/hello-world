# springboot-multi-modules
springboot 多模块项目。

## 开始
1. 执行 `docker compose up -d` 启动 mysql（mariadb）
2. 进入docker容器，执行 `mysql -u root -p123456@Mysql --default-character-set=utf8`进入DB命令行
3. 执行init.sql内容初始化数据库
4. 执行 DemoAppApplication main 方法启动项目
5. 访问 `http://localhost:8080/user/list` 