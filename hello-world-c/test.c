//
// Created by Linwei Zhang on 2022/4/18.
//
// 临时写代码验证

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cjson/cJSON.h"

struct Person
{
    char name[20];
    int age;
};

int main() {

    printf("%d\n", 0x0100);


//    int32_t a = 3232299786;
//    uint8_t *p = (uint8_t *)&a;
//    printf("%02x\n",p[0]);//不进行&运算也可以
//    printf("%02x\n",p[1]);
//    printf("%02x\n",p[2]);
//    printf("%02x\n",p[3]);

    //structure variable declaratio with initialisation
    struct Person person={"Deniss Ritchie", 60};
    //declare character buffer (byte array)
    unsigned char *buffer=(char*)malloc(sizeof(person));
    int i;

    //copying....
    memcpy(buffer,(const unsigned char*)&person,sizeof(person));

    //printing..
    printf("Copied byte array is:\n");
    for(i=0;i<sizeof(person);i++)
        printf("%02X ",buffer[i]);
    printf("\n");

    //freeing memory..
    free(buffer);

    return 0;
}