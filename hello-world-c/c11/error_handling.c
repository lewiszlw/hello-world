//
// Created by Linwei Zhang on 2022/3/28.
//

#include <stdio.h>
#include <errno.h>
#include <string.h>

extern int errno;

int main() {
    FILE *fp = fopen("nonexistent.txt", "r");
    if (fp == NULL) {
        int errnum = errno;
        fprintf(stderr, "Error opening file, error no: %d\n", errnum);
        fprintf(stderr, "Error opening file, error message: %s\n", strerror(errnum));
        return 1;
    }
}