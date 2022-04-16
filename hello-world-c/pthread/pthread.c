//
// Created by Linwei Zhang on 2022/4/13.
//

#include <stdio.h>
#include <pthread.h>
#include <unistd.h>

void hello(char *name) {
    printf("Hello, %s!\n", name);
}

int num = 0;
int end = 100;
void count1() {
    while (num < end) {
        if (num % 2 == 1) {
            printf("count1: %d\n", num);
            num ++;
        }
        sleep(1); // sleep 1 second
    }
}
void count2() {
    while (num < end) {
        if (num % 2 == 0) {
            printf("count2: %d\n", num);
            num ++;
        }
        sleep(1);
    }
}

int main() {
    pthread_t tid;
    int ret = pthread_create(&tid, NULL, (void *)hello, "Tom"); // 第一个参数是线程id，第二个参数是线程属性，第三个参数是线程函数，第四个参数是线程函数的参数
    if (ret != 0) {
        printf("Create thread error!\n");
        return -1;
    }

    // 交替打印奇偶数
    pthread_t t1;
    pthread_t t2;
    pthread_create(&t1, NULL, (void *)count1, NULL);
    pthread_create(&t2, NULL, (void *)count2, NULL);

    // pthread_exit 用于显式地退出一个线程。通常情况下，pthread_exit() 函数是在线程完成工作后无需继续存在时被调用
    // 等各个线程退出后，进程才结束，否则进程强制结束了，线程可能还没反应过来
    pthread_exit(NULL);
}