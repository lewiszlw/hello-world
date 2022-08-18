use std::sync::{Arc, Mutex};

extern crate lazy_static;

lazy_static::lazy_static! {
    static ref RUNTIME: Arc<Mutex<tokio::runtime::Runtime>> = Arc::new(Mutex::new(tokio::runtime::Runtime::new().unwrap()));
}

struct Connection {
    data: Vec<u8>,
}

fn main() {
    for i in 0..100 {
        std::thread::spawn(move || {
            let conn = Connection::new();
            Connection::start(Box::new(conn));
        });
    }

    loop {}
}

impl Connection {
    fn new() -> Connection {
        let conn = Connection {
            data: Vec::new(),
        };
        conn
    }

    fn start(arc: Box<Connection>) {
        let mut runtime = RUNTIME.lock().unwrap();
        runtime.spawn(Connection::read_data(arc));
    }

    async fn read_data(mut conn: Box<Connection>) {
        tokio::time::sleep(std::time::Duration::from_secs(5)).await;
        println!("read data at {:#?}", std::time::Instant::now());
        conn.data.append(&mut vec![1, 2, 3, 4, 5]);
    }
}
