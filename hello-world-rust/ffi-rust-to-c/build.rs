extern crate cc;

fn main() {
    cc::Build::new()
        .include("src")  // 头文件夹
        .file("src/math.c")  // 源文件
        .compile("libmath.a");
}