use futures_util::{SinkExt, StreamExt};
use log::*;
use tokio_tungstenite::{connect_async, tungstenite::{Message, Error as WsError}};
use tokio_tungstenite::tungstenite::handshake::client::Request;
use crate::tts_request::*;

mod tts_request;

// 请求tts服务器合成语音
#[tokio::main]
async fn main() {
    // 配置日志实现
    if std::env::var_os("RUST_LOG").is_none() {
        std::env::set_var("RUST_LOG", "info");
    }
    env_logger::init();

    // 建立websocket连接
    let req = Request::builder()
        .uri("ws://localhost:9003")
        .header("host", "localhost")
        .header("upgrade", "websocket")
        .header("connection", "Upgrade")
        .header("sec-websocket-key", "x3JJHMbDL1EzLkh9GBhXDw==")
        .header("sec-websocket-version", "13")
        .header("X-NLS-Token", "default")
        .header("X-NLS-Levels", "[xiaoai_amy]")
        .header("X-NLS-SessionId", "lwztestsessionid")
        .body(())
        .unwrap();
    let (mut ws_stream, _) = connect_async(req).await.expect("failed to connect");
    info!("Websocket connected");

    // 模拟发送tts文字
    let text = "您好，我是智能客服，了解到您近期使用过空调产品的维修服务，和您做个服务回访，如果让您用“满意、一般、不满意”来评价本次服务，您会选择哪一项呢？";
    let msg = Message::Text(NlsSpeechRequest::new(text.to_string()).to_json());
    ws_stream.send(msg).await.unwrap();

    if let Some(item) = ws_stream.next().await {
        match item {
            Ok(msg) => {
                match msg {
                    Message::Text(text) => {
                        info!("Received text message: {}", text);
                    },
                    Message::Binary(data) => {
                        // 接收tts合成音频流并写入文件
                        info!("Received binary message");
                        std::fs::write("tts_client.pcm", &data).unwrap();
                    },
                    Message::Close(frame) => {
                        info!("Received close message: {:?}", frame);
                        if let Err(e) = ws_stream.close(None).await {
                            match e {
                                WsError::ConnectionClosed => (),
                                _ => {
                                    error!("Error while closing: {:?}", e);
                                },
                            }
                        }
                        info!("Sent close message.");
                    },
                    _ => (),
                }
            },
            Err(e) => {
                error!("Error receiving message: {:?}", e);
            }
        }
    } else {
        info!("ws_stream next is none")
    }

    ws_stream.close(None).await.unwrap();
    info!("Sent close message.");
    while let Some(item) = ws_stream.next().await {
        match item {
            Ok(msg) => {
                match msg {
                    Message::Close(frame) => {
                        info!("Received close message: {:?}", frame);
                        break;
                    },
                    _ => (),
                }
            },
            Err(e) => {
                error!("Error receiving message: {:?}", e);
            }
        }
    }
    info!("Closing...");
}