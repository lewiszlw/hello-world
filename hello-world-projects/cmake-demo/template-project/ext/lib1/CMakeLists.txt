cmake_minimum_required(VERSION 3.20)
project(lib1)

add_library(lib1 STATIC src/lib1.c)
target_include_directories(lib1 PUBLIC include)

set (LIBRARY_OUTPUT_PATH ${CMAKE_CURRENT_SOURCE_DIR}/lib)
