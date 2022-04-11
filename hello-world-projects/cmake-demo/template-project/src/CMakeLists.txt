# 递归地处理src目录下的子目录
add_subdirectory(common)
add_subdirectory(module1)
add_subdirectory(module2)

# 指定要生成的静态库以及它的依赖
aux_source_directory (. SRC_LIST)
add_executable (main ${SRC_LIST})
if (DEBUG)  # 若使用Debug模式构建，在预编译时加入DEBUG的宏
    target_compile_definitions (main PRIVATE DEBUG)
endif ()

target_link_directories (main PRIVATE ${CMAKE_BINARY_DIR}/lib)
target_link_libraries (main PRIVATE common module1 module2)

# 将可执行文件的输出目录设为build/bin
set (EXECUTABLE_OUTPUT_PATH ${CMAKE_BINARY_DIR}/bin)