//
// Created by Linwei Zhang on 2022/3/28.
//

#include <stdio.h>
#include <string.h>

int main() {
    char str[1];
    printf("请输入字符串: ");
    scanf("%s", str);  // 可以输入任意长度字符串
    printf("已输入: %s, len: %lu\n", str, strlen(str));

    int a, b, c;
    printf("请输入三个整数（空格隔开）: ");
    scanf("%d %d %d", &a, &b, &c);
    printf("已输入: %d, %d, %d\n", a, b, c);
}