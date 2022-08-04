
fn main() {
    let (tx, rx) = std::sync::mpsc::channel();
    // 创建线程，并发送消息
    std::thread::spawn(move || {
     // 发送一个数字1, send方法返回Result<T,E>，通过unwrap进行快速错误处理
        tx.send(1).unwrap();

     // 下面代码将报错，因为编译器自动推导出通道传递的值是i32类型，那么Option<i32>类型将产生不匹配错误
     // tx.send(Some(1)).unwrap()
    });

    println!("receive {}", rx.recv().unwrap());
}