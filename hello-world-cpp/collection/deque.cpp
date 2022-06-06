//
// Created by Linwei Zhang on 2022/6/6.
//
#include <iostream>
#include <deque>

class Item {
public:
    Item(int index, std::string name) : index(index), name(name) {}
    int index;
    std::string name;
};

int main() {
    std::cout << "Hello, World!" << std::endl;
    std::deque<Item> dq;
    dq.push_back(Item(1, "Tom"));
    dq.push_back(Item(2, "Jack"));
    dq.push_back(Item(3, "Mary"));
    for (auto &item : dq) {
        std::cout << item.index << ": " << item.name << std::endl;
    }

    // 插入一个元素在队首
    std::cout << "Insert element at begin" << std::endl;
    dq.insert(dq.begin(), Item(0, "Bob"));
    std::cout << dq.begin()->index << ": " << dq.begin()->name << std::endl;
    std::cout << dq.back().index << ": " << dq.back().name << std::endl;
    dq.insert(dq.begin() + 10, Item(10, "Bob"));

    // 删除第一个元素
    std::cout << "Delete element at begin" << std::endl;
    dq.erase(dq.begin());
    std::cout << dq.begin()->index << ": " << dq.begin()->name << std::endl;
}