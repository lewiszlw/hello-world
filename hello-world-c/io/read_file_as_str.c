//
// Created by Linwei Zhang on 2022/4/17.
//

#include <stdio.h>
#include <stdlib.h>

char *read_file_as_str(char *filename) {
    FILE *fp = fopen(filename, "r");
    if (fp == NULL) {
        return NULL;
    }
    fseek(fp, 0, SEEK_END);  // 将文件指针移动到文件末尾
    long fsize = ftell(fp);  // 获取文件大小
    fseek(fp, 0, SEEK_SET);  // 将文件指针移动到文件开头
    char *string = malloc(fsize + 1);  // 分配内存
    fread(string, fsize, 1, fp);  // 读取文件内容, nitems为要读取的个数, 1为每次读取的大小
    fclose(fp);
    string[fsize] = 0; // 将字符串结束符设置为0
    return string;
}

int main() {
    FILE *fp = fopen("test.txt", "w");
    fprintf(fp, "Hello, World!\n");
    fclose(fp);
    char *str = read_file_as_str("test.txt");
    printf("%s\n", str);
    free(str);
    return 0;
}
