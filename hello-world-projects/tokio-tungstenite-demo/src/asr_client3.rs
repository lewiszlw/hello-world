use std::{
    env,
    sync::{atomic::AtomicBool, Arc, Mutex, mpsc::TryRecvError},
};

use futures::FutureExt;
use futures_util::{future, pin_mut, StreamExt, SinkExt};
use tokio::io::{AsyncReadExt, AsyncWriteExt};
use tokio_tungstenite::{connect_async, tungstenite::{protocol::Message, connect}};

struct AsrConnection {
    audio_data: Vec<u8>,
    recog_result: Arc<Mutex<String>>,
}

lazy_static::lazy_static! {
    static ref RUNTIME: tokio::runtime::Runtime = tokio::runtime::Builder::new_multi_thread().enable_all().build().unwrap();
}

fn main() {
    let mut asr_conn = AsrConnection {
        audio_data: Vec::new(),
        recog_result: Arc::new(Mutex::new(String::new())),
    };

    let connect_addr = "ws://127.0.0.1:9003".to_string();
    let url = url::Url::parse(&connect_addr).unwrap();
    let (mut ws_stream, _) = connect(url).expect("Failed to connect");
    println!("WebSocket handshake has been successfully completed");
    let ws_stream = Arc::new(Mutex::new(ws_stream));

    let (sender, receiver) = std::sync::mpsc::channel();

    let ws_stream_clone = ws_stream.clone();
    std::thread::spawn(move || {
        loop {
            println!("read loop");
            match receiver.try_recv() {
                Ok(data) => {
                    println!("start to send data to ws");
                    let mut ws = ws_stream_clone.lock().unwrap();
                    ws.write_message(Message::Binary(data));
                    println!("sent data to ws");
                },
                Err(TryRecvError::Empty) => {},
                Err(TryRecvError::Disconnected) => {
                    println!("read break2");
                    break;
                }
            }
            std::thread::sleep(std::time::Duration::from_millis(100));
        }

        loop {
            println!("receive loop");
            let mut ws = ws_stream_clone.lock().unwrap();

            println!("start to receive message");
            if let Ok(message) = ws.read_message() {  // read_message 会阻塞直至读取到消息!!!
                println!("received message: {:?}", message);
            }
            std::thread::sleep(std::time::Duration::from_millis(200));
        }
        println!("thread ends");
    });

    let mut frame_count = 0;
    loop {
        // 收集用户音频
        if frame_count > 100 {
            println!("speak done");
            break;
        }
        let mut frame = vec![10 as u8; 160];
        asr_conn.audio_data.append(&mut frame);
        sender.send(frame);
        println!("send one frame to mpsc");
        frame_count += 1;
        std::thread::sleep(std::time::Duration::from_millis(100));
    }

    drop(sender);

    loop {
        
    }
}