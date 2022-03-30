//
// Created by Linwei Zhang on 2022/3/28.
//

#include <stdio.h>
#include <stdlib.h>

int main() {
    const char* filename = "test.txt";
    FILE *fp = fopen(filename, "w+");
    if (fp == NULL) {
        printf("open file failed\n");
        return -1;
    }
    char* absolutepath = realpath(filename, NULL);
    printf("absolute filepath: %s\n", absolutepath);

    // 文件写入
    fprintf(fp, "hello world\n");
    fputs("less is more\n", fp);
    fclose(fp);

    // 文件读取
    FILE *fp2 = fopen(filename, "r");
    char buf[1024];
    if (fgets(buf, 1024, fp2) != NULL) {
        puts(buf);
    }
    fclose(fp2);
}