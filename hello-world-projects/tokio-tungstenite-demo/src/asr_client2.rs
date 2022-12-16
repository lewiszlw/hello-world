use std::{
    env,
    sync::{atomic::AtomicBool, Arc, Mutex},
};

use flume::r#async;
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

    let (pck_sender, pck_receiver) = flume::unbounded();

    RUNTIME.spawn(async move {
        let connect_addr = "ws://127.0.0.1:9003".to_string();
        let url = url::Url::parse(&connect_addr).unwrap();
        let (mut ws_stream, _) = connect_async(url).await.expect("Failed to connect");
        println!("WebSocket handshake has been successfully completed");
        let (mut sender, mut receiver) = ws_stream.split();

        loop {
            futures::select! {
                msg = receiver.next().fuse() => {
                    // println!("received msg: {}", msg.unwrap().unwrap());
                    match msg {
                        Some(msg) => {
                            match msg {
                                Ok(message) => {
                                    match message {
                                        Message::Text(text) => {
                                            println!("received msg: {}", text);
                                        },
                                        _ => {}
                                    }
                                },
                                Err(e) => {}
                            }
                        },
                        None => {}
                    }
                }
                pck = pck_receiver.recv_async().fuse() => {
                    match pck {
                        Ok(pck) => {
                            sender
                                .send(Message::Binary(
                                    pck
                                ))
                                .await
                                .expect("message could not be sent");
                        }
                        _ => (),
                    }
                }
            }
        }
    });

    let mut frame_count = 0;
    loop {
        // 收集用户音频
        if frame_count > 100 {
            println!("speak done2");
            std::thread::sleep(std::time::Duration::from_millis(1000));
            continue;
        }
        let mut frame = vec![10 as u8; 160];
        asr_conn.audio_data.append(&mut frame);
        pck_sender.send(frame);
        println!("send one frame to mpsc");
        frame_count += 1;
        std::thread::sleep(std::time::Duration::from_millis(100));
    }
}