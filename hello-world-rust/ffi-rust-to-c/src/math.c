#include "math.h"
#include <stdio.h>

int double_input(int input) {
  return input * 2;
}

int calculate_area(struct rectangle *rect) {
  int area = rect->width * rect->height;
  printf("长方形 %s 的面积: %d\n", rect->name, area);
  return area;
}