//
// Created by Linwei Zhang on 2022/4/9.
//

#include <stdio.h>
#include "static/hello.h"
#include "shared/hi.h"
#include <cjson/cJSON.h>

int main() {
    printf("Hello, Library!\n");
    char *name = "Tom";
    print_hello(name);
    print_hi(name);

    cJSON *root = cJSON_CreateObject();
    cJSON_AddStringToObject(root, "name", "Linwei");
    printf("json: %s\n", cJSON_Print(root));
}