cmake_minimum_required(VERSION 3.12)
project(hello_world_c C)

set(CMAKE_C_STANDARD 11)

add_subdirectory(c11)
add_subdirectory(string)
add_subdirectory(socket)
add_subdirectory(pthread)
add_subdirectory(io)