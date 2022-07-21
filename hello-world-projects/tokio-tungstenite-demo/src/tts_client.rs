use futures_util::{SinkExt, StreamExt};
use log::*;
use tokio_tungstenite::{connect_async, tungstenite::{Message, Error as WsError}};

// 请求tts服务器合成语音
#[tokio::main]
async fn main() {
    // 配置日志实现
    if std::env::var_os("RUST_LOG").is_none() {
        std::env::set_var("RUST_LOG", "info");
    }
    env_logger::init();

    // 建立websocket连接
    let url = url::Url::parse("ws://localhost:9002").unwrap();
    let (mut ws_stream, _) = connect_async(url).await.expect("failed to connect");
    info!("Websocket connected");

    let text = "您好，我是小米智能客服，了解到您近期使用过空调产品的维修服务，和您做个服务回访，如果让您用“满意、一般、不满意”来评价本次服务，您会选择哪一项呢？";
    let msg = Message::Text(text.to_string());
    ws_stream.send(msg).await.unwrap();

    if let Some(item) = ws_stream.next().await {
        match item {
            Ok(msg) => {
                match msg {
                    Message::Text(text) => {
                        println!("Received text message: {}", text);
                    },
                    Message::Close(frame) => {
                        info!("Received close message: {:?}", frame);
                        if let Err(e) = ws_stream.close(None).await {
                            match e {
                                WsError::ConnectionClosed => (),
                                _ => {
                                    error!("Error while closing: {}", e);
                                },
                            }
                        }
                        info!("Sent close message.");
                    },
                    _ => (),
                }
            },
            Err(e) => {
                error!("Error receiving message: \n{0:?}\n{0}", e);
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
                error!("Error receiving message: \n{0:?}\n{0}", e);
            }
        }
    }
    info!("Closing...");
}