#![allow(non_upper_case_globals)]
#![allow(non_camel_case_types)]
#![allow(non_snake_case)]

// 利用宏将生成的绑定引入
include!(concat!(env!("OUT_DIR"), "/bindings.rs"));

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_plus_one() {
        assert_eq!(3, unsafe {plus_one(2) });
    }

    #[test]
    fn test_plus_two() {
        assert_eq!(4, unsafe {plus_two(2) });
    }
}