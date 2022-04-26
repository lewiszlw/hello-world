//
// Created by Linwei Zhang on 2022/4/25.
//

#include <iostream>

int main() {
    int a = 1;
    int &b = a;
    std::cout << &a << std::endl;
    std::cout << a << std::endl;
    std::cout << &b << std::endl;
    std::cout << b << std::endl;
    return 0;
}