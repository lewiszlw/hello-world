//
// Created by Linwei Zhang on 2022/3/15.
//

#include "stdio.h"
#include "string.h"
#include "stdlib.h"

// 方法1：使用strcpy和strcat拼接
void concat1();
// 方法2：使用sprintf拼接
void concat2();

int main() {
    concat1();
    concat2();
    return 0;
}

void concat1() {
    char *first_name = "Lebron";
    char *last_name = "James";

    char *full_name = (char *) malloc(strlen(first_name) + 1 + strlen(last_name));
    // 先将第一个字符串复制到缓冲区
    strcpy(full_name, first_name);
    // 将其他字符串与缓冲区进行拼接
    strcat(full_name, " ");
    strcat(full_name, last_name);

    printf("%s\n", full_name);
}

void concat2() {
    char *first_name = "Lebron";
    char *last_name = "James";

    char *full_name = (char *) malloc(strlen(first_name) + 1 + strlen(last_name));
    sprintf(full_name, "%s%s%s", first_name, " ", last_name);

    printf("%s\n", full_name);
}