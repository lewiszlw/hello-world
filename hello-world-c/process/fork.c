//
// Created by Linwei Zhang on 2022/4/20.
//

#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

int main() {
    int count = 0;
    pid_t pid;
    int rv;
    if ((pid = fork()) < 0) {
        return -1;
    } else if (pid == 0) {
        // child process
        count++;
        printf("I am child process, pid = %d\n", getpid());
        printf("I am child process, parent pid = %d\n", getppid());
    } else {
        // parent process
        count++;
        wait(&rv);
        printf("I am parent process, wait child process return value = %d\n", rv);
        printf("I am parent process, pid = %d\n", getpid());
        printf("I am parent process, child pid = %d\n", pid);
    }
    printf("current pid = %d, count = %d\n", getpid(), count);
}