use futures_util::{SinkExt, StreamExt};
use log::*;
use std::net::SocketAddr;
use tokio::net::{TcpListener, TcpStream};
use tokio_tungstenite::{accept_async, tungstenite::Error};
use tokio_tungstenite::tungstenite::Message;

/// 从本地pcm文件读取返回音频流

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

    // let (mut ws_sender, mut ws_receiver) = ws_stream.split();
    while let Some(item) = ws_stream.next().await {
        match item {
            Ok(msg) => {
                match msg {
                    Message::Text(text) => {
                        info!("Received message: {}", text);
                        ws_stream.send(Message::Binary(std::fs::read("/Users/lewis/Github/hello-world/hello-world-projects/tokio-tungstenite-demo/assets/demo-8kHz.pcm").unwrap())).await.unwrap();
                        info!("Sent pcm data")
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
                error!("Error receiving message: {}", e);
            }
        }
    }
}