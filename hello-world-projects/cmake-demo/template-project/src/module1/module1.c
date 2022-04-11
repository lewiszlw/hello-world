//
// Created by Linwei Zhang on 2022/4/11.
//

#include <stdio.h>
#include "module1/module1.h"
#include "common/common.h"
#include "lib1.h"

int module1_func(char *str) {
    printf("module1_func, %s\n", str);
    common_func("module1_func");
    lib1_func("module1_func");
    return 0;
}

int module1_calc(int a, int b) {
    return a - b;
}