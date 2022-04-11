//
// Created by Linwei Zhang on 2022/4/11.
//

#include <stdio.h>
#include "module2/module2.h"

int module2_func(char *str) {
    printf("module2_func, %s\n", str);
    return 0;
}

int module2_calc(int a, int b) {
    return a * b;
}