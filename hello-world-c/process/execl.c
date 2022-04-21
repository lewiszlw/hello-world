//
// Created by Linwei Zhang on 2022/4/20.
//

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <string.h>

#define STDIN_FILENO    0       /* Standard input.  */
#define STDOUT_FILENO   1       /* Standard output.  */
#define STDERR_FILENO   2       /* Standard error output.  */
#define MAXLINE 4096

int main() {
//    execl("/bin/ls", "ls", "-l", NULL); // 执行ls -l

    // 执行python脚本 https://stackoverflow.com/questions/3653521/how-to-execute-python-script-from-c-code-using-execl
    int  n, parent_child_pipe[2], child_parent_pipe[2];
    pid_t pid;
    char line[MAXLINE];
    int rv;  // 子进程返回值

    if (pipe(parent_child_pipe) < 0 || pipe(child_parent_pipe) < 0)  // 创建管道
        puts("Error creating pipes...\n");

    if ( (pid = fork()) < 0)  // 创建子进程
        puts("Error forking...\n");
    else if (pid > 0) { /* PARENT */
        close(parent_child_pipe[0]);  // 关闭父进程到子进程的管道读端
        close(child_parent_pipe[1]);  // 关闭子进程到父进程的管道写端
        while (fgets(line, MAXLINE, stdin) != NULL) {  // 从标准输入读取数据
            n = strlen(line);
            printf("read from stdin, line: %s, n: %d\n", line, n);
            if (write(parent_child_pipe[1], line, n) != n) // 将数据写入管道
                puts("write error to pipe...\n");
            close(parent_child_pipe[1]);  // 关闭父进程到子进程的管道写端
            wait(&rv);  // 等待子进程结束，返回子进程的退出状态
            if ( (n = read(child_parent_pipe[0], line, MAXLINE)) < 0)  // 从管道读取数据
                puts("read error from pipe...\n");
            if (n == 0) {
                puts("child closed pipe...\n");
                break;
            }
            printf("read from pipe, line: %s, n: %d\n", line, n);
            line[n] = '\0'; /* null terminate */  // 字符串结束符
            if (fputs(line, stdout) == EOF) // 将数据写入标准输出
                puts("fputs error...\n");
        }
        if (ferror(stdin))  // 检查标准输入是否出错
            puts("fgets error on stdin...\n");
        exit(0);

    } else {  /* CHILD */
        close(parent_child_pipe[1]);  // 关闭父进程到子进程的管道写端
        close(child_parent_pipe[0]);  // 关闭子进程到父进程的管道读端
        // 将父进程到子进程的管道读端重定向到标准输入
        if (parent_child_pipe[0] != STDIN_FILENO) {
            if (dup2(parent_child_pipe[0], STDIN_FILENO) != STDIN_FILENO)
                puts("dup2 error to stdin...\n");
            close(parent_child_pipe[0]);
        }
        // 将子进程到父进程的管道写端重定向到标准输出
        if (child_parent_pipe[1] != STDOUT_FILENO) {
            if (dup2(child_parent_pipe[1], STDOUT_FILENO) != STDOUT_FILENO)
                puts("dup2 error to stdout...\n");
            close(child_parent_pipe[1]);
        }
        if (execl("./NullFilter.py", "./NullFilter.py", (char *) 0) < 0)
            puts("execl error...\n");
    }
}