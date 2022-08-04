extern crate libc;
use std::sync::Arc;

// 需要跟C中结构体对应
#[repr(C)]   // 保证结构体的布局与平台的 C 的表示方法相兼容
pub struct rectangle {
    pub width: libc::c_uint,
    pub height: libc::c_uint,
    pub name: *const libc::c_char,
    pub data1: Box<Data>,  // 传递Box指针
    pub data2: Arc<Data>,  // 传递Arc指针
    pub data3: String,    // 传递String
    pub data4: i32,       // 传递i32
}

#[derive(Debug)]
pub struct Data {
    pub id: String,
    pub value: Vec<u8>,
}

extern "C" {
    fn double_input(input: libc::c_int) -> libc::c_int;
    fn calculate_area(rect: *const rectangle) -> libc::c_int;
}

fn main() {
    let input = 4;
    let output = unsafe {double_input(input)};
    println!("{} * 2 = {}", input, output);

    let data1 = Data {
        id: "1".to_string(),
        value: vec![1],
    };
    let data2 = Data {
        id: "1".to_string(),
        value: vec![1, 2],
    };

    let rect = rectangle {
        width: 10,
        height: 20,
        name: std::ffi::CString::new("rectangle").unwrap().into_raw(),
        data1: Box::new(data1),
        data2: Arc::new(data2),
        data3: "data3".to_string(),
        data4: 100,
    };

    let area = unsafe {calculate_area(&rect)};
    println!("Area of rectangle: {}", area);
    println!("{:?}", *(rect.data1));
    println!("{:?}", *(rect.data2));
    println!("{:?}", rect.data3);
    println!("{:?}", rect.data4);
}