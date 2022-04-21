add_executable(fork fork.c)

add_executable(execl execl.c)
# 将python脚本复制到build目录中
configure_file(NullFilter.py ${CMAKE_CURRENT_BINARY_DIR}/NullFilter.py COPYONLY)
