//
// Created by Linwei Zhang on 2022/4/8.
//

#include <stdio.h>
#include "hello.h"

int main() {
    printf("Hello, CMake!\n");

    char *name = "Tom";
    hello(name);

    return 0;
}