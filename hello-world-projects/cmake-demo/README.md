# cmake-demo
学习 CMake 来构建 C/C++ 语言程序。

- https://github.com/ttroy50/cmake-examples
- https://github.com/SFUMECJF/cmake-examples-Chinese
- https://juejin.cn/post/6844904000484016135

## hello-cmake 
学习 CMake 基本使用。

编译运行
1. `cd hello-cmake`进入CMakeLists.txt所在目录
2. `mkdir build` 创建build目录
3. `cmake -B build`显式指定build目录（或者进入build目录执行 `cmake ..`）
4. `cmake --build build` 在build目录下执行编译（或者进入build目录执行 `cmake --build .`）
5. `./build/hello-cmake` 执行编译后的程序

可通过 message() 在生成阶段打印各种信息。

## hello-library
学习如何生成、链接和使用各种库。

多个 CMakeLists.txt 时，CLion 中可右键指定 CMakeLists.txt 来 reload project。

- PRIVATE - 目录被添加到目标（库）的包含路径中。
- INTERFACE - 目录没有被添加到目标（库）的包含路径中，而是链接了这个库的其他目标（库或者可执行程序）包含路径中
- PUBLIC - 目录既被添加到目标（库）的包含路径中，同时添加到了链接了这个库的其他目标（库或者可执行程序）的包含路径中

引入第三方库
1. 安装第三方库 cJSON
```shell
cd deps/cJSON
mkdir build
cd build
cmake ..
make
sudo make install
```
2. 链接第三方库
```
target_link_libraries(hello_library PRIVATE ${CJSON_LIBRARIES})
```

通过 install 安装到本地
```
install(TARGETS hello_library DESTINATION ${CMAKE_INSTALL_PREFIX}/bin)
```
编译后执行 `sudo make install` 来执行安装。

## hello-unit-testing
学习如何使用 CMake 来编译和测试 C/C++ 单元测试。

采用 googletest 来编写单测
1. 创建 .cc 单测文件
2. 编写 CMakeLists.txt 生成可执行文件并链接gtest和待测试库，配置单元测试
3. 引入 gtest 头文件，以及通过 extern "C" 引入C语言头文件，然后编写单测

执行单元测试
```shell
mkdir build && cd build
cmake ..
make test # 或者 ctest 执行单元测试
```

## template-project
CMake 模板项目。
- https://github.com/Modern-CMake-CN/Modern-CMake-zh_CN/blob/master/chapters/basics/structure.md
- https://zhuanlan.zhihu.com/p/493250684

项目结构
```
.
├── CMakeLists.txt
├── cmake
│   └── FindSomeLib.cmake
├── conf
│   └── config.json
├── ext
│   └── lib1
│       ├── CMakeLists.txt
│       ├── include
│       │   └── lib1.h
│       ├── lib
│       │   └── liblib1.a
│       └── src
│           └── lib1.c
├── include
│   └── template-project
│       ├── common
│       │   └── common.h
│       ├── module1
│       │   └── module1.h
│       └── module2
│           └── module2.h
├── src
│   ├── CMakeLists.txt
│   ├── common
│   │   ├── CMakeLists.txt
│   │   └── common.c
│   ├── main.c
│   ├── module1
│   │   ├── CMakeLists.txt
│   │   └── module1.c
│   └── module2
│       ├── CMakeLists.txt
│       └── module2.c
└── test
    ├── CMakeLists.txt
    ├── common
    │   └── common_test.cc
    ├── module1
    │   └── module1_test.cc
    └── module2
        └── module2_test.cc
```
- include 项目头文件
- src 项目源文件
- test 项目测试文件
- ext 外部依赖库
- conf 项目配置文件
- cmake cmake相关的文件（如Findxxx.cmake）

编译测试
```shell
mkdir build && cd build
cmake -DTEST=ON ..
make
make test
./bin/main
```