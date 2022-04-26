//
// Created by Linwei Zhang on 2022/4/22.
//
#include <iostream>

int main() {
    char s1[] = "hello";
    char s2[] = {'h', 'e', 'l', 'l', 'o'};  // 错误
    char s3[] = {'h', 'e', 'l', 'l', 'o', '\0'};
    std::string s4 = "hello";
    std::cout << s1 << std::endl;
    std::cout << s2 << std::endl;
    std::cout << s3 << std::endl;
    std::cout << s4 << std::endl;

    return 0;
}