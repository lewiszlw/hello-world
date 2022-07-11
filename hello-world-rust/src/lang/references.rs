// 引用（reference）像一个指针，因为它是一个地址，我们可以由此访问储存于该地址的属于其他变量的数据。
// 与指针不同，引用确保指向某个特定类型的有效值。
fn main() {

    let s1 = String::from("hello");
    let len = calculate_length(&s1);
    println!("The length of '{}' is {}.", s1, len);

    let mut s2 = String::from("hello");
    change(&mut s2);
    println!("changed s2: {}", s2);
}


fn calculate_length(s: &String) -> usize { // 引用默认不可变
    s.len()
} // 这里，s 离开了作用域。但因为它并不拥有引用值的所有权，所以什么也不会发生


fn change(some_string: &mut String) {  // 可变引用（在同一时间只能有一个对某一特定数据的可变引用）
    some_string.push_str(", world");
}