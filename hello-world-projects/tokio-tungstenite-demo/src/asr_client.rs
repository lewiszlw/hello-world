//! A simple example of hooking up stdin/stdout to a WebSocket stream.
//!
//! This example will connect to a server specified in the argument list and
//! then forward all data read on stdin to the server, printing out all data
//! received on stdout.
//!
//! Note that this is not currently optimized for performance, especially around
//! buffer management. Rather it's intended to show an example of working with a
//! client.
//!
//! You can use this example together with the `server` example.

use std::env;

use futures_util::{future, pin_mut, StreamExt};
use tokio::io::{AsyncReadExt, AsyncWriteExt};
use tokio_tungstenite::{connect_async, tungstenite::protocol::Message};

#[tokio::main]
async fn main() {
    let connect_addr = "ws://127.0.0.1:9003".to_string();
    let url = url::Url::parse(&connect_addr).unwrap();

    let (sender, receiver) = futures_channel::mpsc::unbounded();
    tokio::spawn(read_audio(sender));

    let (ws_stream, _) = connect_async(url).await.expect("Failed to connect");
    println!("WebSocket handshake has been successfully completed");

    let (ws_write, ws_read) = ws_stream.split();

    // mpsc receiver 重定向到 ws_write
    let receiver_to_ws = receiver.map(Ok).forward(ws_write);
    
    let ws_to_stdout = {
        ws_read.for_each(|message| async {
            let data = message.unwrap().into_data();
            tokio::io::stdout().write_all(&data).await.unwrap();
        })
    };

    pin_mut!(receiver_to_ws, ws_to_stdout);
    future::select(receiver_to_ws, ws_to_stdout).await;
}

// Our helper method which will read data from stdin and send it along the
// sender provided.
async fn read_audio(tx: futures_channel::mpsc::UnboundedSender<Message>) {
    loop {
        // 读取asr音频数据
        let mut buf = vec![1; 1024];
        tx.unbounded_send(Message::binary(buf)).unwrap();
    }
}
