cmake_minimum_required(VERSION 3.20)
project(hello_library C)

set(CMAKE_C_STANDARD 11)

# 创建静态库
add_library(static_library_hello STATIC src/hello.c)
target_include_directories(static_library_hello PUBLIC ${PROJECT_SOURCE_DIR}/include)  # 这里如改成 PRIVATE，则链接了这个库的 hello_library target 无法找到 include 目录

# 创建动态库
add_library(shared_library_hi SHARED src/hi.c)
target_include_directories(shared_library_hi PUBLIC ${PROJECT_SOURCE_DIR}/include)  # 这里如改成 PRIVATE，则链接了这个库的 hello_library target 无法找到 include 目录

# 查找第三方库
find_package(cJSON REQUIRED)
if (cJSON_FOUND)
    message(STATUS "cJSON found")
    message("CJSON_INCLUDE_DIRS: ${CJSON_INCLUDE_DIRS}")
    message("CJSON_LIBRARIES: ${CJSON_LIBRARIES}")
else()
    message(FATAL_ERROR "cJSON not found")
endif()

add_executable(hello_library src/main.c)
# 链接静态库到 hello_library target 上
target_link_libraries(hello_library PRIVATE static_library_hello)
# 链接动态库到 hello_library target 上
target_link_libraries(hello_library PRIVATE shared_library_hi)
# 链接第三方库到 hello_library target 上
target_link_libraries(hello_library PRIVATE ${CJSON_LIBRARIES})
