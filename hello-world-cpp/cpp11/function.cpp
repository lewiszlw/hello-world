//
// Created by Linwei Zhang on 2022/4/25.
//

#include <iostream>

// 函数默认参数
int sum(int a, int b = 10) {
    return a + b;
}

// Lambda表达式
auto lambda_f = [](int a, int b = 10) {
    return a + b;
};

int main() {
    std::cout << sum(1, 2) << " " << lambda_f(1, 2) << std::endl;
    std::cout << sum(1) << " " << lambda_f(1) <<std::endl;
}