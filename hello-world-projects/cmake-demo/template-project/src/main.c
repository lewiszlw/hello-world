//
// Created by Linwei Zhang on 2022/4/11.
//

#include <stdio.h>
#include "common/common.h"
#include "module1/module1.h"
#include "module2/module2.h"

int main() {
    printf("Hello World!\n");

    common_func("a");
    module1_func("b");
    module2_func("c");
    return 0;
}