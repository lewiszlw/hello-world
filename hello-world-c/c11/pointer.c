//
// Created by Linwei Zhang on 2022/3/25.
//

#include <stdio.h>
#include <stdlib.h>

int max(int, int);
int next_value(void);
void init_array(int *, int, int (*)(void));

int main() {

    int var1 = 10;
    int *p1 = &var1;
    printf("var1变量地址：%p\n", &var1);
    printf("p1（指针指向的地址）：%p\n", p1);
    printf("*p1（指针指向地址的值）：%d\n", *p1);

    // 函数指针
    int (*p2)(int, int) = max;
    printf("1, 2, 3 最大值：%d\n", p2(p2(1, 2), 3));
    int arr[5];
    init_array(arr, 5, next_value);
    printf("arr[0]：%d\n", arr[0]);
    printf("arr[5]：%d\n", arr[5]);

}

int max(int a, int b) {
    return a > b ? a : b;
}
int next_value() {
    return rand();
}
void init_array(int *arr, int size, int (*next_value)(void)) {
    for (int i = 0; i < size; i++) {
        arr[i] = next_value();
    }
}
