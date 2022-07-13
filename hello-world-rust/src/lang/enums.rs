#[derive(Debug)]
enum Message {
    Quit,  // 类单元结构体
    Move { x: i32, y: i32 },
    Write(String),  // 元组结构体
    ChangeColor(i32, i32, i32),  // 元组结构体
}

fn main() {
    let m = Message::Write(String::from("hello"));
    match m {
        Message::Quit => println!("Quit"),
        Message::Move { x, y} => println!("Move {} {}", x, y),
        Message::Write(s) => println!("Write {}", s),
        Message::ChangeColor(r, g, b) => println!("ChangeColor {} {} {}", r, g, b),
    }
}