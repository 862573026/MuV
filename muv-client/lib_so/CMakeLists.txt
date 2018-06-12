
cmake_minimum_required(VERSION 3.4.1)

#设置生成的so动态库最后的输出路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/lib_so/../jniLibs/${ANDROID_ABI})


#=======================================================================
#========================= [ 系统Library引入 ] =========================
#=======================================================================

find_library( # 系统log
              log-lib
              log )

find_library( # 系统图像处理
              jnigraphics-lib
              jnigraphics )

#=======================================================================
#========================= [     加密Lib     ] =========================
#=======================================================================

add_library( # 设置名称
             encrypt
             # Sets the library as a shared library.
             SHARED
             # Provides a relative path to your source file(s).
             src/main/cpp/EncryptJni.cpp
             src/main/cpp/encrypt/md5.cpp
             src/main/cpp/encrypt/aes.c
             src/main/cpp/encrypt/base64.c)

 target_link_libraries(
                        encrypt
                        ${log-lib} )
