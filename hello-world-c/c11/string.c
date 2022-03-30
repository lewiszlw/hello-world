//
// Created by Linwei Zhang on 2022/3/26.
//

#include <stdio.h>
#include <string.h>

int main() {

    char str1[] = "Hello";
    printf("str1=%s, len=%lu\n", str1, strlen(str1));
    char str2[10] = "World";
    printf("str2=%s, len=%lu\n", str2, strlen(str2));
    char str3[100] = {'p', 'r', 'o'};
    printf("str3=%s, len=%lu\n", str3, strlen(str3));
    char str4[100] = {'p', 'r', 'o', '\0'};  //str4=pro
    printf("str4=%s, len=%lu\n", str4, strlen(str4));
    char str5[100] = {"p", "r", "o"};  // str5=p
    printf("str5=%s, len=%lu\n", str5, strlen(str5));

    char str6[] = "Hello";
    char str7[] = "Hi";
    strcpy(str6, str7);
    printf("str6=%s, len=%lu\n", str6, strlen(str6));
}