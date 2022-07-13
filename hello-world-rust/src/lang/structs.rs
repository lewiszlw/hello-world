#[derive(Debug)]
struct Rectangle {
    width: u32,
    height: u32,
}

impl Rectangle {
    fn area(&self) -> u32 {
        self.width * self.height
    }
    fn square(size: u32) -> Rectangle {
        Rectangle {
            width: size,
            height: size,
        }
    }
}

// 元组结构体
struct Color(i32, i32, i32);

// 类单元结构体
struct AlwaysEqual;

fn main() {
    let rect1 = Rectangle {
        width: 30,
        height: 50,
    };
    println!("rect1 is {:?}", rect1);
    println!("The area of the rectangle is {} square pixels.", area(&rect1));
    println!("The area of the rectangle is {} square pixels.", rect1.area());

    let rect2 = Rectangle::square(40);

    let black = Color(0, 0, 0);
}

fn area(rectangle: &Rectangle) -> u32 {
    rectangle.width * rectangle.height
}