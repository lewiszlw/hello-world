//
// Created by Linwei Zhang on 2022/4/25.
//

#include <iostream>

// 抛异常
double divide(double a, double b) {
    if (b == 0) {
        throw std::runtime_error("Divide by zero");
    }
    return a / b;
}


// 自定义异常
class MyException : public std::exception {
private:
    const char *msg_;
public:
    MyException(const char *msg) : msg_(msg) {}

    const char *what() const noexcept override {
        return msg_;
    }
};

int main() {
    try {
        std::cout << divide(1, 0) << std::endl;
    } catch (std::runtime_error &e) {
        std::cout << e.what() << std::endl;
    }

    try {
        throw "Exception happened";
    } catch (const char *e) {
        std::cout << e << std::endl;
    }

    try {
        throw MyException("My exception happened");
    } catch (MyException &e) {
        std::cout << e.what() << std::endl;
    }
    return 0;
}