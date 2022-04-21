//
// Created by Linwei Zhang on 2022/4/18.
//
// 临时写代码验证

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cjson/cJSON.h"

int main() {
    char *json = "{\"intention\":\"1\"}";
    cJSON *root = cJSON_Parse(json);
    printf("bbb\n");
    if (cJSON_HasObjectItem(root, "intention")) {
        printf("aaa\n");
    }
    return 0;
}