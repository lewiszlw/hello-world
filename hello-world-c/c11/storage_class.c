//
// Created by Linwei Zhang on 2022/3/23.
//

#include "stdio.h"

int get_static_variable();
extern int extern_add(int, int);

int main() {
    printf("get_static_variable : %d\n", get_static_variable());
    printf("get_static_variable : %d\n", get_static_variable());
    printf("get_static_variable : %d\n", get_static_variable());

    printf("extern_add: %d\n", extern_add(1, 2));
    return 0;
}

int get_static_variable() {
    static int INDEX = 1;
    // 函数每调用一次INDEX加1一次
    INDEX ++;
    return INDEX;
}
