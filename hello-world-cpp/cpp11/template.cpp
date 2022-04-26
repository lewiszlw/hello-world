//
// Created by Linwei Zhang on 2022/4/25.
//

#include <iostream>
#include <vector>


// 函数模板
template <typename T>
inline T const& Max (T const& a, T const& b) {
    return a > b ? a : b;
}


// 类模板
template <class T>
class Stack {
private:
    std::vector<T> elems;
public:
    void push(T const&);  // T const& 为引用类型
    T pop() const;
};
template <class T>
void Stack<T>::push(T const& element) {
    elems.push_back(element);
};
template <class T>
T Stack<T>::pop() const {
    T back = elems.back();
//    elems.pop_back();  // 引用类型不能直接调用pop_back()
    return back;
}


int main() {
    std::cout << Max(1, 2) << std::endl;
    std::cout << Max(1.2f, 2.2f) << std::endl;
    std::cout << Max("abc", "cde") << std::endl;

    Stack<int> int_stack;
    int_stack.push(1);
    int_stack.push(2);
    std::cout << int_stack.pop() << std::endl;

    Stack<float> float_stack;
    float_stack.push(1.2f);
    float_stack.push(2.2f);
    std::cout << float_stack.pop() << std::endl;

    Stack<std::string> string_stack;
    string_stack.push("abc");
    string_stack.push("cde");
    std::cout << string_stack.pop() << std::endl;
    return 0;
}