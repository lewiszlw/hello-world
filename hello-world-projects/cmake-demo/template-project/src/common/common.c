//
// Created by Linwei Zhang on 2022/4/11.
//

#include <stdio.h>
#include "common/common.h"
#include "lib1.h"

int common_func(char *str) {
    printf("common_func, %s\n", str);
    lib1_func("common_func");
    return 0;
}

int common_calc(int a, int b) {
    return a + b;
}