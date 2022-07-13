#include "clib2.h"

int plus_two(int x) {
    x = plus_one(x);
    x = plus_one(x);
    return x;
}