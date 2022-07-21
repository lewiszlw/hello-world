use futures_util::{SinkExt, StreamExt};
use log::*;
use std::{net::SocketAddr, time::Duration};
use tokio::net::{TcpListener, TcpStream};
use tokio_tungstenite::{accept_async, tungstenite::Error};
use tokio_tungstenite::tungstenite::{Message, Result};

#[tokio::main]
async fn main() {
    // 配置日志实现
    if std::env::var_os("RUST_LOG").is_none() {
        std::env::set_var("RUST_LOG", "info");
    }
    env_logger::init();

    // 监听端口
    let addr = "127.0.0.1:9002";
    let listener = TcpListener::bind(&addr).await.expect("Can't listen");
    info!("Listening on: {}", addr);

    while let Ok((stream, _)) = listener.accept().await {
        let peer = stream.peer_addr().expect("connected streams should have a peer address");
        info!("Peer address: {}", peer);

        // spawn 创建一个 Tokio 任务（一个异步的绿色线程），spawn 函数的参数是一个 async 语句块
        tokio::spawn(accept_connection(peer, stream));
    }
}

async fn accept_connection(peer: SocketAddr, stream: TcpStream) {
    // 接收TCP连接并处理
    if let Err(e) = handle_connection(peer, stream).await {
        match e {
            Error::ConnectionClosed | Error::Protocol(_) | Error::Utf8 => (),
            err => error!("Error processing connection: {}", err),
        }
    }
}

async fn handle_connection(peer: SocketAddr, stream: TcpStream) -> Result<()> {
    // websocket握手
    let ws_stream = accept_async(stream).await.expect("Failed to accept");
    info!("New WebSocket connection: {}", peer);

    let (mut ws_sender, mut ws_receiver) = ws_stream.split();

    let mut interval = tokio::time::interval(Duration::from_secs(3));

    loop {
        // select! 同时等待多个计算操作，然后当其中一个操作完成时就退出等待
        tokio::select! {
            msg = ws_receiver.next() => {
                match msg {
                    Some(msg) => {
                        let msg = msg?;
                        if msg.is_text() || msg.is_binary() {
                            info!("Received message: {}", msg);
                            // echo返回相同消息给客户端
                            ws_sender.send(msg).await?;
                        } else if msg.is_close() {
                            info!("Received close message");
                            if let Err(e) = ws_sender.close().await {
                                    match e {
                                        Error::ConnectionClosed => (),
                                        _ => {
                                            info!("Error while closing: {}", e);
                                            break;
                                        },
                                    }
                                }
                            break;
                        }
                    }
                    None => break,
                }
            }
            _ = interval.tick() => {
                ws_sender.send(Message::Text("tick".to_owned())).await?;
            }
        }
    }

    Ok(())
}
