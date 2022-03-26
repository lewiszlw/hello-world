//
// Created by Linwei Zhang on 2022/3/24.
//

#include "stdio.h"

typedef enum Day {
    // 0   1   3      4    5    6    7
    MON, TUE, WED=3, THU, FRI, SAT, SUN
} Day;

// 需要通过 typedef 才能这样传递枚举类型参数
void weather(Day);

int main() {
    printf("MON is %d\n", MON);
    printf("TUE is %d\n", TUE);
    printf("WED is %d\n", WED);
    printf("THU is %d\n", THU);
    printf("FRI is %d\n", FRI);
    printf("SAT is %d\n", SAT);
    printf("SUN is %d\n", SUN);

    enum Day today = MON;
    weather(today);
    weather(TUE);
}

void weather(Day day) {
    if (day == MON) {
        printf("The weather of this day is not good\n");
    } else {
        printf("The weather of this day is good\n");
    }
}

