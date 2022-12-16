extern crate websocket;

use std::io::stdin;
use std::sync::{Arc, Mutex};
use std::sync::mpsc::{channel, TryRecvError};
use std::thread;

use websocket::client::ClientBuilder;
use websocket::{Message, OwnedMessage};

struct AsrConnection {
    audio_data: Vec<u8>,
    recog_result: Arc<Mutex<String>>,
}

const CONNECTION: &'static str = "ws://127.0.0.1:9003";

// 参考 https://github.com/websockets-rs/rust-websocket/blob/master/examples/client.rs
fn main() {
	let client = ClientBuilder::new(CONNECTION)
		.unwrap()
		.add_protocol("rust-websocket")
		.connect_insecure()
		.unwrap();
	println!("Successfully connected");

	let (mut ws_read, mut ws_write) = client.split().unwrap();

	// mpsc
	let (sender, receiver) = channel();

	thread::spawn(move || {
		loop {
			// Send loop
			match receiver.try_recv() {
				Ok(data) => {
					match ws_write.send_message(&OwnedMessage::Binary(data)) {
						Ok(_) => { println!("send one frame")},
						Err(e) => { println!("failed to send one frame")}
					}
				},
				Err(TryRecvError::Empty) => {
				}
				Err(TryRecvError::Disconnected) => {
					break;
				}
			}
		}
	});

	thread::spawn(move || {
		// Receive loop
		for message in ws_read.incoming_messages() {
			let message = match message {
				Ok(m) => m,
				Err(e) => {
					println!("Receive Loop: {:?}", e);
					return;
				}
			};
			match message {
				OwnedMessage::Close(_) => {
					// Got a close message, so send a close message and return
					return;
				}
				// Say what we received
				_ => println!("Receive Loop: {:?}", message),
			}
		}
	});

	let mut frame_count = 0;
    loop {
        // 收集用户音频
        if frame_count > 100 {
            println!("speak done");
            break;
        }
        let mut frame = vec![10 as u8; 160];
        sender.send(frame);
        println!("send one frame to mpsc");
        frame_count += 1;
        std::thread::sleep(std::time::Duration::from_millis(100));
    }

    drop(sender);

    loop {
        
    }
}