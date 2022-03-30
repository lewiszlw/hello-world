# hello-world-c
C 语言学习
- https://docs.microsoft.com/zh-cn/cpp/c-language/
- https://www.runoob.com/cprogramming/c-tutorial.html

## get started
1. 新建 .c 文件
2. 修改 CMakeLists.txt，增加 add_executable
3. 通过 CLion 来编译执行 .c 文件 main 方法

命令行编译
1. 项目下新建 build 目录并进入目录
2. 在 build 目录下执行 `/Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake ..` 命令生成 Makefile 文件
3. 在 build 目录下执行 `make` / `make clean && make` 进行编译得到可执行文件