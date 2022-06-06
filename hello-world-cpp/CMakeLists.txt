cmake_minimum_required(VERSION 3.20)
project(hello_world_cpp)

add_subdirectory(cpp11)
add_subdirectory(cpp20)
add_subdirectory(timer)
add_subdirectory(collection)

add_executable(test test.cpp)
