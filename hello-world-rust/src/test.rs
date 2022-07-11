use std::net::TcpListener;
fn main() {
    TcpListener::bind("localhost:8080").unwrap();
    println!("Hello, world!");
}