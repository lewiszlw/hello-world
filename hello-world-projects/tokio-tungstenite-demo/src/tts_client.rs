use std::fs::File;
use std::io::Write;
use futures_util::{SinkExt, StreamExt};
use log::*;
use tokio::net::TcpStream;
use tokio_tungstenite::{connect_async, MaybeTlsStream, tungstenite::{Message, Error as WsError}, WebSocketStream};
use tokio_tungstenite::tungstenite::handshake::client::Request;
use crate::tts_request::*;

mod tts_request;

/// 请求tts服务器合成语音

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

    while let Some(item) = ws_stream.next().await {
        match item {
            Ok(msg) => {
                match msg {
                    Message::Text(text) => {
                        info!("Received text message: {}", text);
                        let nls_speech_response: NlsSpeechResponse = NlsSpeechResponse::from_json(&text);
                        if nls_speech_response.header.status == 20000000 && nls_speech_response.header.name == "SynthesisCompleted" {
                            ws_close(&mut ws_stream).await;
                        }
                    },
                    Message::Binary(data) => {
                        // 接收tts合成音频流并写入文件
                        info!("Received binary message");
                        std::fs::File::options().append(true).open("tts_client.pcm").unwrap().write(&data).unwrap();
                    },
                    Message::Close(frame) => {
                        info!("Received close message: {:?}", frame);
                        ws_close(&mut ws_stream);
                        break;
                    },
                    _ => (),
                }
            },
            Err(e) => {
                error!("Error receiving message: {:?}", e);
                break;
            }
        }
    }

}

async fn ws_close(ws_stream: &mut WebSocketStream<MaybeTlsStream<TcpStream>>) {
    if let Err(e) = ws_stream.close(None).await {
        match e {
            WsError::ConnectionClosed => (),
            _ => {
                error!("Error while closing: {:?}", e);
            },
        }
    }
    info!("Sent close message.");
}