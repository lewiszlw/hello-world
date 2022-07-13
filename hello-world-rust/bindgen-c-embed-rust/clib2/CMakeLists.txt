add_library(clib2 src/clib2.c)
target_include_directories(clib2 PUBLIC include)
target_include_directories(clib2 PUBLIC ${CMAKE_SOURCE_DIR}/clib1/include)
target_link_libraries(clib2 PUBLIC clib1)