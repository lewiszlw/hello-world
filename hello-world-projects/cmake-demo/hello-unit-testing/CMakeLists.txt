cmake_minimum_required(VERSION 3.20)
project(hello_unit_testing)

set(CMAKE_C_STANDARD 11)
set(CMAKE_CXX_STANDARD 14)

# 从github下载googletest依赖
include(FetchContent)
FetchContent_Declare(
        googletest
        URL https://github.com/google/googletest/archive/609281088cfefc76f9d0ce82e1ff6c30cc3591e5.zip
)
FetchContent_MakeAvailable(googletest)

add_library(calc src/calc.c)
target_include_directories(calc PUBLIC ${PROJECT_SOURCE_DIR}/include)

add_executable(hello_unit_testing src/main.c)
target_link_libraries(hello_unit_testing calc)

# 开启测试
enable_testing()
# 生成可执行文件
add_executable(calc_test tests/calc_test.cc)
target_link_libraries(calc_test calc gtest_main)
# 发现测试用例
include(GoogleTest)
gtest_discover_tests(calc_test)
