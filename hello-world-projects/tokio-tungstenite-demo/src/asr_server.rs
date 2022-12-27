use futures_util::{SinkExt, StreamExt};
use log::*;
use std::net::SocketAddr;
use tokio::net::{TcpListener, TcpStream};
use tokio_tungstenite::{accept_async, tungstenite::Error};
use tokio_tungstenite::tungstenite::Message;

/// 返回asr识别结果

#[tokio::main]
async fn main() {
    // 配置日志实现
    if std::env::var_os("RUST_LOG").is_none() {
        std::env::set_var("RUST_LOG", "info");
    }
    env_logger::init();

    // 监听端口
    let addr = "127.0.0.1:9003";
    let listener = TcpListener::bind(&addr).await.expect("Can't listen");
    info!("Listening on: {}", addr);

    while let Ok((stream, client_addr)) = listener.accept().await {
        info!("Client address: {}", client_addr);

        // spawn 创建一个 Tokio 任务（一个异步的绿色线程），spawn 函数的参数是一个 async 语句块
        tokio::spawn(accept_connection(client_addr, stream));
    }
}

async fn accept_connection(peer: SocketAddr, stream: TcpStream) {
    // websocket握手
    let mut ws_stream = accept_async(stream).await.expect("Failed to accept ws");
    info!("New WebSocket connection: {}", peer);

    let mut total_frames = 0;
    while let Some(item) = ws_stream.next().await {
        match item {
            Ok(msg) => {
                match msg {
                    Message::Text(text) => {
                        info!("Received text message: {}", text);
                        if text.contains("RecognitionStart") {
                            info!("Received RecognitionStart message");
                        }
                    },
                    Message::Binary(data) => {
                        info!("Received binary message, size {}", data.len());
                        total_frames += 1;
                        // 发送asr识别结果
                        if total_frames % 10 == 0 {
                            ws_stream.send(Message::Text("asr识别结果".to_string())).await.unwrap();
                        }
                    }
                    Message::Close(_) => {
                        info!("Received close message.");
                        if let Err(e) = ws_stream.close(None).await {
                            match e {
                                Error::ConnectionClosed => (),
                                _ => {
                                    info!("Error while closing: {:?}", e);
                                    break;
                                },
                            }
                        }
                    }
                    _ => (),
                }
            }
            Err(e) => {
                error!("Error receiving message: {:?}", e);
            }
        }
    }
}