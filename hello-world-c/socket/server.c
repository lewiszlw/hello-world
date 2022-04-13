//
// Created by Linwei Zhang on 2022/4/12.
//

#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <unistd.h>

int main() {
    // 1. 创建套接字
    int sockfd = socket(AF_INET, SOCK_STREAM, 0); // AF_INET 表示 IPv4 地址, 流格式套接字(SOCK_STREAM)也叫“面向连接的套接字”, 0表示使用默认的协议(即TCP)
    if (sockfd < 0) {
        perror("socket create failed");
        return 1;
    }

    // 2. 绑定地址
    struct sockaddr_in addr;
    addr.sin_family = AF_INET;  // 使用IPv4地址
    addr.sin_port = htons(8888);  // 端口
    addr.sin_addr.s_addr = htonl(INADDR_ANY); // 服务器IP地址--允许连接到所有本地地址上 (INADDR_ANY表示任意地址)
    int ret = bind(sockfd, (struct sockaddr *) &addr, sizeof(addr));
    if (ret < 0) {
        perror("bind failed");
        return 1;
    }

    // 3. 监听
    ret = listen(sockfd, 5); // 监听队列长度为5
    if (ret < 0) {
        perror("listen failed");
        return 1;
    }

    // 4. 接收连接
    struct sockaddr_in client_addr;
    socklen_t client_addr_len = sizeof(client_addr);
    int connfd = accept(sockfd, (struct sockaddr *) &client_addr, &client_addr_len);
    if (connfd < 0) {
        perror("accept failed");
        return 1;
    }

    // 5. 发送数据
    char str[] = "hello world";
    ret = send(connfd, str, sizeof(str), 0);  // 0表示默认值 (默认值为非阻塞)
    if (ret < 0) {
        perror("send failed");
        return 1;
    }

    // 6. 接收数据
    char buf[1024];
    ssize_t n = recv(connfd, buf, sizeof(buf), 0);
    if (n < 0) {
        perror("recv failed");
        return 1;
    }
    buf[n] = '\0'; // 字符串结束符
    printf("data: %s\n", buf);

    // 7. 关闭套接字
    close(connfd);
    close(sockfd);

    return 0;
}
