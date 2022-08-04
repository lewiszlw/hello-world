use std::thread;
use std::sync::Arc;
use std::sync::Mutex;


struct MyInner { s: String }
struct My { inner: Arc<Mutex<MyInner>> }

impl My {

	fn new(s: String) -> My {

		My {inner: Arc::new(Mutex::new(MyInner {s: s}))}
	}

	fn start(&mut self) {

		let local_self = self.inner.clone();

		thread::spawn(move || {

			local_self.lock().unwrap().test();
		});
	}
}

impl MyInner {
	
	fn test(&mut self) {

		println!("{:?}", self.s);
		self.s = String::from("and this is a modified string");
		println!("{:?}", self.s);
	}
}

fn main() {

	let mut thread_test = My::new(String::from("this is a string"));
	thread_test.start();

	loop {};
}