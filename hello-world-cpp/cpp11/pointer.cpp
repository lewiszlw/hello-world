//
// Created by Linwei Zhang on 2022/4/25.
//

#include <iostream>

int main() {
    // new delete
    int *p = new int(42);
    std::cout << *p << std::endl;
    delete p;

    // new[] delete[]
    int *p2 = new int[10];
    p2[0] = 1;
    std::cout << *p2 << std::endl;
    delete[] p2;

    // 空指针
    int *null_ptr = nullptr;
    std::cout << null_ptr << std::endl;

    return 0;
}