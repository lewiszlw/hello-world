cmake_minimum_required(VERSION 3.20)
project(template_project)

# 指定位于ext文件夹内的依赖库的路径
set(LIB1_INC_DIR ${CMAKE_CURRENT_SOURCE_DIR}/lib/lib1/include)
set(LIB1_LIB_DIR ${CMAKE_CURRENT_SOURCE_DIR}/lib/lib1/lib)

# 读取构建选项
option(DEBUG "Debug build option" ON)
option(TEST "Test option" ON)

add_subdirectory(src)
add_subdirectory(ext/lib1)
if(TEST)
    enable_testing()   # 启用CTest
    add_subdirectory(test)
endif()