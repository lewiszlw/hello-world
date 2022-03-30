//
// Created by Linwei Zhang on 2022/3/30.
//

#include <stdio.h>
#include <stdarg.h>

// 值传递
void swap1(int x, int y);
// 引用传递
void swap2(int *x, int *y);
// 可变参数
double avg(int num, ...);

int main() {
    int x = 100, y = 200;

    swap1(x, y);
    printf("值传递swap1后 x = %d, y = %d\n", x, y);

    swap2(&x, &y);
    printf("引用传递swap2后 x = %d, y = %d\n", x, y);

    printf("可变参数avg of 4,5,6 = %.2f\n", avg(3, 4, 5, 6));
    return 0;
}

void swap1(int x, int y) {
    int temp = x;
    x = y;
    y = temp;
}

void swap2(int *x, int *y) {
    int temp = *x;
    *x = *y;
    *y = temp;
}
double avg(int num, ...) {
    va_list valist;
    double sum = 0;
    // 为 num 个参数初始化 valist
    va_start(valist, num);
    for (int i = 0; i < num; i++) {
        sum += va_arg(valist, int);
    }
    // 清理 valist
    va_end(valist);
    return sum / num;
}