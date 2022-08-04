extern crate libc;
extern crate url;

use std::fmt::format;
use std::sync::Mutex;
use std::{sync, thread, time};
use std::sync::atomic::{AtomicBool, Ordering};
use std::io::Write;

pub struct Timer {
    handle: Option<thread::JoinHandle<()>>,
    alive: sync::Arc<AtomicBool>,
    data: sync::Arc<Mutex<String>>,
}

impl Timer {
    pub fn new() -> Timer {
        Timer {
            handle: None,
            alive: std::sync::Arc::new(AtomicBool::new(false)),
            data: std::sync::Arc::new(Mutex::new(String::new())),
        }
    }

    pub fn start<F>(&mut self, fun: F)
        where F: 'static + Send + FnMut() -> ()
    {
        self.alive.store(true, Ordering::SeqCst);

        let alive = self.alive.clone();
        let data = self.data.clone();

        self.handle = Some(thread::spawn(move || {
            let mut fun = fun;
            while alive.load(Ordering::SeqCst) {
                fun();
                data.lock().unwrap().push_str("1");
                thread::sleep(time::Duration::from_millis(10));
            }
        }));
    }

    pub fn stop(&mut self) {
        self.alive.store(false, Ordering::SeqCst);
        self.handle
            .take().expect("Called stop on non-running thread")
            .join().expect("Could not join spawned thread");
    }
}

fn main1() {
    let mut timer = Timer::new();
    timer.start(|| println!("Hello, World!") );

    println!("Feeling sleepy...");
    thread::sleep(time::Duration::from_millis(50));

    println!("Time for dinner!");
    timer.stop();
    println!("final data: {:?}", timer.data.lock().unwrap());
}

fn main() {
    // std::fs::File::create("test.txt").unwrap();
    let start = std::time::SystemTime::now();
    let since_the_epoch = start
        .duration_since(std::time::UNIX_EPOCH)
        .expect("Time went backwards");
    let ms = since_the_epoch.as_secs() as i64 * 1000i64 + (since_the_epoch.subsec_nanos() as f64 / 1_000_000.0) as i64;
    let fp = format!("{}-{}.txt", "test", ms.to_string());
    std::fs::write(fp, "Hello, World!".as_bytes()).unwrap();
}