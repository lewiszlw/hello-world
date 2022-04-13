//
// Created by Linwei Zhang on 2022/4/12.
//

#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

int main() {
    // 1. 创建套接字
    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) {
        perror("socket create failed");
        return 1;
    }

    // 2. 连接服务器
    struct sockaddr_in servaddr;
    servaddr.sin_family = AF_INET; // IPv4
    servaddr.sin_port = htons(8888); // 服务器端口
    servaddr.sin_addr.s_addr = inet_addr("127.0.0.1"); // 服务器的ip地址
    int ret = connect(sockfd, (struct sockaddr *) &servaddr, sizeof(servaddr));
    if (ret < 0) {
        perror("connect failed");
        return 1;
    }

    // 3. 读取服务器发送的消息
    char buf[1024] = {0};
    ret = read(sockfd, buf, sizeof(buf));
    if (ret < 0) {
        perror("read failed");
        return 1;
    }
    printf("Message from server: %s\n", buf);

    // 4. 发送数据
    char sendbuf[1024] = {0};
    while (fgets(sendbuf, sizeof(sendbuf), stdin) != NULL) { // 控制台输入
        send(sockfd, sendbuf, strlen(sendbuf), 0);
    }

    // 5. 关闭套接字
    close(sockfd);

    return 0;
}