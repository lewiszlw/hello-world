//
// Created by Linwei Zhang on 2022/3/24.
//

#include <stdio.h>

int main() {
    // 按位与
    printf("0&0=%d\n", 0&0);
    printf("0&1=%d\n", 0&1);
    printf("1&0=%d\n", 1&0);
    printf("1&1=%d\n", 1&1);

    // 按位或
    printf("0|0=%d\n", 0|0);
    printf("0|1=%d\n", 0|1);
    printf("1|0=%d\n", 1|0);
    printf("1|1=%d\n", 1|1);

    // 按位异或
    printf("0^0=%d\n", 0^0);
    printf("0^1=%d\n", 0^1);
    printf("1^0=%d\n", 1^0);
    printf("1^1=%d\n", 1^1);

    // 按位取反
    // 所有正整数的按位取反是其本身+1的负数
    // 所有负整数的按位取反是其本身+1的绝对值
    // 零的按位取反是 -1
    printf("~-2=%d\n", ~-2);
    printf("~-1=%d\n", ~-1);
    printf("~0=%d\n", ~0);
    printf("~1=%d\n", ~1);
    printf("~2=%d\n", ~2);

    // 逻辑取反
    printf("!0=%d\n", !0);
    printf("!1=%d\n", !1);
    printf("!2=%d\n", !2);

    // 返回变量地址
    int a = 1;
    printf("&a=0X%p\n", &a);
}