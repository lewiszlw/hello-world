# 设置运行此配置文件所需的CMake最低版本
cmake_minimum_required(VERSION 3.20)
# 设置工程名
project(hello_cmake LANGUAGES C VERSION 0.1 DESCRIPTION "This is a hello cmake project")

# 设置语言标准 C11
set(CMAKE_C_STANDARD 11)

# 创建SOURCES变量包含所有源文件
set(SOURCES
        src/main.c
        src/hello.c)

# 生成可执行文件
# 等同于 add_executable(hello_cmake src/main.c src/hello.c)
add_executable(hello_cmake ${SOURCES})

# 设置生成可执行文件需要include的路径
target_include_directories(hello_cmake PRIVATE ${PROJECT_SOURCE_DIR}/include)

message("test printing variables")
message("PROJECT_SOURCE_DIR = ${PROJECT_SOURCE_DIR}")
message("PROJECT_BINARY_DIR = ${PROJECT_BINARY_DIR}")
message("CMAKE_BINARY_DIR = ${CMAKE_BINARY_DIR}")
message("PROJECT_NAME = ${PROJECT_NAME}")
message("PROJECT_VERSION = ${PROJECT_VERSION}")
message("PROJECT_DESCRIPTION = ${PROJECT_DESCRIPTION}")
