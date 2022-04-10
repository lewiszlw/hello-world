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
