fn main() {
    let s = String::from("hello world");
    let hello = &s[0..5];
    let world = &s[6..11];
    let first_world = first_word(&s);

    // s.clear(); // 清空字符串，会报错
    // 当拥有某值的不可变引用时，就不能再获取一个可变引用。
    // 因为 clear 需要清空 String，它尝试获取一个可变引用。
    // 在调用 clear 之后的 println! 使用了 word 中的引用，
    // 所以这个不可变的引用在此时必须仍然有效

    println!("{}, {}, {}", hello, world, first_world);

    let s2 = "Hello";  // 字符串字面值也是&str
}
fn first_word(s: &String) -> &str {
    let bytes = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[0..i];
        }
    }

    &s[..]
}