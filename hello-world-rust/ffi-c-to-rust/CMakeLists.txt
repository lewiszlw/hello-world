cmake_minimum_required(VERSION 3.12)
project(ffi-c-to-rust C)

add_executable(main src/main.c)
target_link_libraries(main PUBLIC ${CMAKE_CURRENT_SOURCE_DIR}/target/debug/libdouble_input.a pthread ${CMAKE_DL_LIBS})