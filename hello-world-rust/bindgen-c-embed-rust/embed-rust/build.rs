extern crate bindgen;

use std::env;
use std::path::PathBuf;

fn main() {
    println!("cargo:rerun-if-changed=wrapper.h");

    // 链接 C 库，用于 Rust 调用 C 函数
    println!("cargo:rustc-link-lib=clib1");
    println!("cargo:rustc-link-lib=clib2");
    println!("cargo:rustc-link-search=/Users/lewis/Github/hello-world/hello-world-rust/bindgen-c-embed-rust/build/clib1");
    println!("cargo:rustc-link-search=/Users/lewis/Github/hello-world/hello-world-rust/bindgen-c-embed-rust/build/clib2");

    let bindings = bindgen::Builder::default()
        .header("wrapper.h")
        .clang_arg("-I/Users/lewis/Github/hello-world/hello-world-rust/bindgen-c-embed-rust/clib1/include")  // 解决bindgen无法找到头文件的问题
        .clang_arg("-I/Users/lewis/Github/hello-world/hello-world-rust/bindgen-c-embed-rust/clib2/include")
        .generate()
        .expect("Unable to generate bindings");
    
        let output_path = PathBuf::from(env::var("OUT_DIR").unwrap());  // $OUT_DIR 是 cargo 的一个环境变量，通常在类似于 target/debug/build/{crate-package-name}-xxxxxxxxx/ 目录下
        bindings.write_to_file(output_path.join("bindings.rs"))
            .expect("Couldn't write bindings!");
        
}