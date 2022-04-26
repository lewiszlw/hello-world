//
// Created by Linwei Zhang on 2022/4/25.
//
#include <iostream>

// 类声明定义
class Student {
private:
    std::string name;
    int age;
public:
    Student(std::string name, int age) : name(name), age(age) {}
    std::string getName() { return name; }
    int getAge(); // 成员函数声明
};
int Student::getAge() { return age; }  // 成员函数定义


// 类继承
class Animal {
protected:
    std::string name;
public:
    Animal(std::string name) : name(name) {}
    void eat() { std::cout << name << " eat" << std::endl; }
};
class Dog : public Animal {
public:
    Dog(std::string name) : Animal(name) {}
    void sleep() { std::cout << name << " sleep" << std::endl; }
};


// 抽象类
class Shape {
public:
    virtual void area() = 0;  // 纯虚函数
};

int main() {
    Student s("Tom", 20);
    std::cout << s.getName() << " " << s.getAge() << std::endl;

    Dog d("Dog");
    d.eat();

    return 0;
}