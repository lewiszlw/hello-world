cmake_minimum_required(VERSION 3.12)
project(hello_world_c C)

set(CMAKE_C_STANDARD 11)

add_executable(hello_world_c main.c)
add_executable(str_concat string/str_concat.c)
add_executable(preprocessor c11/preprocessor.c)
add_executable(storage_class c11/storage_class.c)
add_executable(operator c11/operator.c)
add_executable(enum c11/enum.c)
add_executable(pointer c11/pointer.c)