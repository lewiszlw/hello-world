extern crate libc;

// 需要跟C中结构体对应
#[repr(C)]   // 保证结构体的布局与平台的 C 的表示方法相兼容
pub struct rectangle {
    pub width: libc::c_uint,
    pub height: libc::c_uint,
    pub name: *const libc::c_char,
}

extern "C" {
    fn double_input(input: libc::c_int) -> libc::c_int;
    fn calculate_area(rect: *const rectangle) -> libc::c_int;
}

fn main() {
    let input = 4;
    let output = unsafe {double_input(input)};
    println!("{} * 2 = {}", input, output);

    let rect = rectangle {
        width: 10,
        height: 20,
        name: std::ffi::CString::new("rectangle").unwrap().into_raw(),
    };

    let area = unsafe {calculate_area(&rect)};
    println!("Area of rectangle: {}", area);
}