fn main() {
    // if判断
    let num = 6;
    if num < 5 {
        println!("condition was true");
    } else {
        println!("condition was false");
    }

    // loop循环
    let loop_num = 3;
    let mut counter1 = 0;
    loop {
        println!("again!");
        counter1 += 1;
        if counter1 >= loop_num {
            break;
        }
    }

    // loop循环返回值
    let mut counter2 = 0;
    let result = loop {
        counter2 += 1;

        if counter2 == 10 {
            break counter2 * 2;
        }
    };
    println!("The result is {result}");

    // while循环
    let mut number = 3;
    while number != 0 {
        println!("{number}!");
        number -= 1;
    }
}