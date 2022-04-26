//
// Created by Linwei Zhang on 2022/4/25.
//

#include <iostream>

namespace my_namespace {
    int a = 1;
    void hello(std::string name) {
        std::cout << "Hello, " << name << std::endl;
    }
}

namespace your_namespace {
    int a = 2;
    void hello(std::string name) {
        std::cout << "Hi, " << name << std::endl;
    }
}

int main() {
    my_namespace::hello("Tom");
    your_namespace::hello("Tom");
    std::cout << my_namespace::a << std::endl;
    std::cout << your_namespace::a << std::endl;

    using namespace my_namespace;
    hello("Tom");
    return 0;
}