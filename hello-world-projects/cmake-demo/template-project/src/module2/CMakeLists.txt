# 指定要生成的静态库以及它的依赖
aux_source_directory (. SRC_LIST)
add_library (module2 STATIC ${SRC_LIST})
target_include_directories (module2 PUBLIC ${PROJECT_SOURCE_DIR}/include/template-project)

# 将上述静态库的输出目录设为build/lib
set (LIBRARY_OUTPUT_PATH ${CMAKE_BINARY_DIR}/lib)