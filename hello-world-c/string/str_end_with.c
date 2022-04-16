//
// Created by Linwei Zhang on 2022/4/17.
//

#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool str_end_with(const char *str, const char *suffix) {
    if (str == NULL || suffix == NULL) {
        return false;
    }
    size_t str_len = strlen(str);
    size_t suffix_len = strlen(suffix);
    if (str_len < suffix_len) {
        return false;
    }
    for (int i = 0; i < suffix_len; i++) {
        if (str[str_len - suffix_len + i] != suffix[i]) {
            return false;
        }
    }
    return true;
}

int main() {
    char str[] = "hello world";
    char suffix[] = "world";
    printf("%s\n", str_end_with(str, suffix) ? "true" : "false");

    char suffix2[] = "helle";
    printf("%s\n", str_end_with(str, suffix2) ? "true" : "false");
    return 0;
}