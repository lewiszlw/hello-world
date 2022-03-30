//
// Created by Linwei Zhang on 2022/3/23.
//

#include <stdio.h>

#define VALUE 100

#ifndef MESSAGE
    #define MESSAGE "Hello World"
#endif

#define MESSAGE_LENGTH (sizeof(MESSAGE) - 1)

#define SQUARE(x, y) ((x) * (y))

#define MAX(x, y) ((x) > (y) ? (x) : (y))


int main() {
    printf("value: %d, square: %d, max of (value, 10): %d\n", VALUE, SQUARE(VALUE, VALUE), MAX(VALUE, 10));
    printf("message: %s, length: %d\n", MESSAGE, MESSAGE_LENGTH);
    printf("Date: %s\n", __DATE__);
    printf("Time: %s\n", __TIME__);
    printf("File: %s\n", __FILE__);
    printf("Line: %d\n", __LINE__);
    printf("Compiler: %s\n", __VERSION__);
    printf("ANSIC: %d\n", __STDC__);
    return 0;
}