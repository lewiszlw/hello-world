//
// Created by Linwei Zhang on 2022/6/15.
//

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

char* url_decode(const char *encoded_url)
{
    char *decoded_url = (char *) malloc(strlen(encoded_url) + 1);
    char *dst = decoded_url;
    char a, b;
    while (*encoded_url) {
        if ((*encoded_url == '%') &&
            ((a = encoded_url[1]) && (b = encoded_url[2])) &&
            (isxdigit(a) && isxdigit(b))) {
            if (a >= 'a')
                a -= 'a'-'A';
            if (a >= 'A')
                a -= ('A' - 10);
            else
                a -= '0';
            if (b >= 'a')
                b -= 'a'-'A';
            if (b >= 'A')
                b -= ('A' - 10);
            else
                b -= '0';
            *dst++ = 16*a+b;
            encoded_url+=3;
        } else if (*encoded_url == '+') {
            *dst++ = ' ';
            encoded_url++;
        } else {
            *dst++ = *encoded_url++;
        }
    }
    *dst++ = '\0';
    return decoded_url;
}

int main() {
    char *encoded_url = "http://www.example.com//cgi-bin/student.py?name=%E5%BC%A0%E6%9E%97%E4%BC%9F";
    char *decoded_url = url_decode(encoded_url);
    printf("decoded_url: %s\n", decoded_url);
}