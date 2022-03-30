//
// Created by Linwei Zhang on 2022/3/28.
//

#include <stdio.h>

struct Person {
    char *name;
    int age;
    unsigned int gender:1;
};

void print_person(struct Person person);
void print_person2(struct Person *ptr);

int main() {
    struct Person person = {"Tom", 20};
    print_person(person);
    struct Person *ptr = &person;
    print_person2(ptr);

    person.gender = 1;
    printf("person.gender = %d\n", person.gender);  // 1
    person.gender = 0;
    printf("person.gender = %d\n", person.gender);  // 1
    person.gender = 2;
    printf("person.gender = %d\n", person.gender);  // 1
}

void print_person(struct Person p) {
    printf("%s is %d years old.\n", p.name, p.age);
}
void print_person2(struct Person *ptr) {
    printf("%s is %d years old.\n", ptr -> name, ptr -> age);
}