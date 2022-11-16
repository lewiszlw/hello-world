#![crate_type = "staticlib"]

use std::rc::Rc;
use std::cell::RefCell;

#[no_mangle]
pub extern "C" fn double_input(input: i32) -> i32 {
    input * 2
}

#[repr(C)]
pub struct Channel {
    pub id: *const libc::c_char,
    pub obj: *mut libc::c_void,
}

#[derive(Debug)]
#[repr(C)]
pub struct ChannelObj {
    pub name: String,
    pub active: bool,
    pub data: Vec<u8>,
}

#[no_mangle]
pub unsafe extern "C" fn create_channel_obj(mut channel: *mut Channel) {
    let channel_obj = ChannelObj {
        name: "ChannelObj".to_string(),
        active: true,
        data: vec![1 as u8; 5],
    };
    let mut channel_obj = Box::new(channel_obj);
    (*channel).obj = Box::into_raw(channel_obj) as *mut libc::c_void;
}

#[no_mangle]
pub unsafe extern "C" fn print_channel_obj(mut channel: *mut Channel) {
    let channel_obj = Box::from_raw((*channel).obj as *mut ChannelObj);
    println!("channel_obj: {:?}", channel_obj);
    // 防止Box被drop掉
    std::mem::forget(channel_obj);
}

#[no_mangle]
pub unsafe extern "C" fn create_channel_obj_rc(mut channel: *mut Channel) {
    let channel_obj = ChannelObj {
        name: "ChannelObj-Rc".to_string(),
        active: true,
        data: vec![2 as u8; 5],
    };
    let mut channel_obj = Rc::new(channel_obj);
    (*channel).obj = Rc::into_raw(channel_obj) as *mut libc::c_void;
}

#[no_mangle]
pub unsafe extern "C" fn print_channel_obj_rc(mut channel: *mut Channel) {
    let channel_obj = Rc::from_raw((*channel).obj as *mut ChannelObj);
    println!("channel_obj_rc: {:?}", channel_obj);
    // 防止Rc被drop掉
    std::mem::forget(channel_obj);
}

#[no_mangle]
pub unsafe extern "C" fn create_channel_obj_box_rc(mut channel: *mut Channel) {
    let channel_obj = ChannelObj {
        name: "ChannelObj-Box-Rc".to_string(),
        active: true,
        data: vec![2 as u8; 5],
    };
    let mut channel_obj = Rc::new(RefCell::new(channel_obj));
    (*channel).obj = Box::into_raw(Box::new(channel_obj)) as *mut libc::c_void;
}

#[no_mangle]
pub unsafe extern "C" fn print_channel_obj_box_rc(mut channel: *mut Channel) {
    let mut channel_obj = &mut *((*channel).obj as *mut Rc<RefCell<ChannelObj>>);
    println!("channel_obj_box_rc: {:?}", channel_obj);
    // 不会被drop掉
    // 但需要手动将Rc引用计数置0来进行drop
}