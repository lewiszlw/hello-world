cmake_minimum_required(VERSION 3.12)
project(bindgen-c-embed-rust C)

add_subdirectory(clib1)
add_subdirectory(clib2)

add_executable(main src/main.c)
target_include_directories(main PUBLIC ${CMAKE_SOURCE_DIR}/clib1/include)
target_include_directories(main PUBLIC ${CMAKE_SOURCE_DIR}/clib2/include)
target_link_libraries(main PUBLIC clib1 clib2)