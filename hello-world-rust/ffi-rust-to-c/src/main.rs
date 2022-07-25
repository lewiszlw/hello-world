extern crate libc;
use std::sync::Arc;

// 需要跟C中结构体对应
#[repr(C)]   // 保证结构体的布局与平台的 C 的表示方法相兼容
pub struct rectangle {
    pub width: libc::c_uint,
    pub height: libc::c_uint,
    pub name: *const libc::c_char,
    pub obj: *mut libc::c_void,   // 传递void*类型的指针
}

#[derive(Debug)]
#[repr(C)]
pub struct config {
    pub id: String,
    pub data: Arc<InternalData>,  // 传递Arc指针
}

#[derive(Debug)]
pub struct InternalData {
    pub name: String,
    pub age: u8,
    pub address: String,
}

extern "C" {
    fn double_input(input: libc::c_int) -> libc::c_int;
    fn calculate_area(rect: *const rectangle) -> libc::c_int;
}

fn main() {
    let input = 4;
    let output = unsafe {double_input(input)};
    println!("{} * 2 = {}", input, output);

    let internal_data = InternalData {
        name: "zhangsan".to_string(),
        age: 18,
        address: "beijing".to_string(),
    };
    let mut config = config {
        id: "1".to_string(),
        data: Arc::new(internal_data),
    };

    let config_ptr = Box::into_raw(Box::new(config));
    let rect = rectangle {
        width: 10,
        height: 20,
        name: std::ffi::CString::new("rectangle").unwrap().into_raw(),
        obj: config_ptr as *const config as *mut libc::c_void,
    };

    let area = unsafe {calculate_area(&rect)};
    println!("Area of rectangle: {}", area);
    println!("{:?}", unsafe {&*(rect.obj as *const config)});
}