fn main() {
    let x = 1;
    println!("The result of plus_one(x) is: {}", plus_one(x));
    println!("The result of plus_two(x) is: {}", plus_two(x));
    
    let y = {
        let x = 3;
        x + 1
    };
    println!("The value of y is: {}", y);
}

fn plus_one(x: i32) -> i32 {
    x + 1
}

fn plus_two(x: i32) -> i32 {
    return x + 2;
}