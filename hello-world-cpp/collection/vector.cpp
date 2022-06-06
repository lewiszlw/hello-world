//
// Created by Linwei Zhang on 2022/6/6.
//

#include <iostream>
#include <vector>

class Item {
public:
    Item(int index, std::string name) : index(index), name(name) {}
    int index;
    std::string name;
};


int main() {
    std::cout << "Hello, World!" << std::endl;
    std::vector<Item> v;
    v.push_back(Item(1, "Tom"));
    v.push_back(Item(2, "Jack"));
    for (auto &item : v) {
        std::cout << item.index << ": " << item.name << std::endl;
    }

    v.insert(v.begin(), Item(0, "Bob"));
    std::cout << v.begin()->index << ": " << v.begin()->name << std::endl;

    for (int i = 0; i < v.size(); ++i) {
        std::cout << v[i].index << ": " << v[i].name << std::endl;
    }
    return 0;
}