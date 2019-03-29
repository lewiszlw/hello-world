cmake_minimum_required(VERSION 3.12)
project(hello_world_c C)

set(CMAKE_C_STANDARD 11)

add_executable(hello_world_c main.c)
add_executable(test test.c)