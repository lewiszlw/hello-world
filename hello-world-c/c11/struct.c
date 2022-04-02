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

struct channel_t {
    int id;
    int *bytes;
};
struct channel_method_vtable_t {
    void (*open)(struct channel_t *channel);
    int (*close)(struct channel_t *channel);
};
void channel_open(struct channel_t *channel);
int channel_close(struct channel_t *channel);
const struct channel_method_vtable_t channel_method_vtable = {
    channel_open,
    channel_close
};


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

    int bytes[] = {1, 2, 3};
    struct channel_t channel = {1, bytes};
    channel_method_vtable.open(&channel);
    channel_method_vtable.close(&channel);
}

void print_person(struct Person p) {
    printf("%s is %d years old.\n", p.name, p.age);
}
void print_person2(struct Person *ptr) {
    printf("%s is %d years old.\n", ptr -> name, ptr -> age);
}

void channel_open(struct channel_t *channel) {
    printf("channel_open\n");
}
int channel_close(struct channel_t *channel) {
    printf("channel_close\n");
    return 0;
}