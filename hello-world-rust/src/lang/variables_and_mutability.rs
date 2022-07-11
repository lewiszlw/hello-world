fn main() {
    let x = 5;
    println!("The value of x is: {}", x);

    // 当再次使用 let 时，实际上创建了一个新变量
    let x = 5 + 1;
    println!("The value of x is: {}", x);

    {
        let x = x * 2;
        println!("The value of x is: {}", x);
    }

    println!("The value of x is: {}", x);
}