//
// Created by Linwei Zhang on 2022/3/28.
//

#include <stdio.h>
#include <string.h>

union Data {
    int i;
    float f;
    char str[20];
    unsigned int _switch:1;  // 开关仅用一位
};

int main() {
    printf("sizeof(union Data) = %d\n", sizeof(union Data));

    union Data data;
    data.i = 123;
    data.f = 3.14f;
    strcpy(data.str, "hello");
    printf("data.i : %d\n", data.i);  // 损坏
    printf("data.f : %f\n", data.f);  // 损坏
    printf("data.str : %s\n", data.str);  // 正常

    data._switch = 1;
    printf("data._switch : %d\n", data._switch);  // 1
    data._switch = 0;
    printf("data._switch : %d\n", data._switch);  // 0
    data._switch = 2;
    printf("data._switch : %d\n", data._switch);  // 0
}
