# 指定要生成的静态库以及它的依赖
aux_source_directory (. SRC_LIST)
add_library (module1 STATIC ${SRC_LIST})
target_include_directories (module1 PUBLIC ${PROJECT_SOURCE_DIR}/include/template-project)
target_include_directories (module1 PUBLIC ${LIB1_INC_DIR})
target_link_directories (module1 PUBLIC ${CMAKE_BINARY_DIR}/lib ${LIB1_LIB_DIR})
target_link_libraries (module1 common lib1)

# 将上述静态库的输出目录设为build/lib
set (LIBRARY_OUTPUT_PATH ${CMAKE_BINARY_DIR}/lib)