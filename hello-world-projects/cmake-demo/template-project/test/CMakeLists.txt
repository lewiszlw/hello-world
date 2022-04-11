# 从github下载googletest依赖
include(FetchContent)
FetchContent_Declare(
        googletest
        URL https://github.com/google/googletest/archive/609281088cfefc76f9d0ce82e1ff6c30cc3591e5.zip
)
FetchContent_MakeAvailable(googletest)

# 生成可执行文件
add_executable(common_test common/common_test.cc)
target_link_directories(common_test PRIVATE ${CMAKE_BINARY_DIR}/lib)
target_link_libraries(common_test common gtest_main)

add_executable(module1_test module1/module1_test.cc)
target_link_directories(module1_test PRIVATE ${CMAKE_BINARY_DIR}/lib)
target_link_libraries(module1_test module1 gtest_main)

add_executable(module2_test module2/module2_test.cc)
target_link_directories(module2_test PRIVATE ${CMAKE_BINARY_DIR}/lib)
target_link_libraries(module2_test module2 gtest_main)

# 发现测试用例
include(GoogleTest)
gtest_discover_tests(common_test)
gtest_discover_tests(module1_test)
gtest_discover_tests(module2_test)
