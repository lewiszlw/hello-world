//
// Created by Linwei Zhang on 2022/4/1.
//

#include <stdio.h>

void pass_arr_method1(int arr[], int size);
void pass_arr_method2(int *arr);
int * return_arr_method();

int main() {
    int arr1[10];
    int arr2[3] = {1, 2, 3};
    int arr3[2][2];
    int arr4[2][2] = {{1, 2}, {3, 4}};

    pass_arr_method1(arr2, 3);
    pass_arr_method2(arr2);
    int *arr5 = return_arr_method();
    printf("return_arr_method arr5[0] = %d, arr5[1] = %d\n", arr5[0], *(arr5 + 1));
}

void pass_arr_method1(int arr[], int size) {
    printf("pass_arr_method1 arr[size - 1] = %d\n", arr[size - 1]);
}
void pass_arr_method2(int *arr) {
    printf("pass_arr_method3 arr[0] = %d\n", arr[0]);
}
int * return_arr_method() {
    int arr[10];
    for (int i = 0; i < 10; ++i) {
        arr[i] = i;
    }
    return arr;
}