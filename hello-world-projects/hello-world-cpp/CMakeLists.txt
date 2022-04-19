cmake_minimum_required(VERSION 3.20)
project(hello_world_cpp)

set(CMAKE_CXX_STANDARD 20)

add_subdirectory(cpp20)

add_executable(test test.cpp)
