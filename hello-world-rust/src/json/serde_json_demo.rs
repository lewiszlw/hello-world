use serde::{Deserialize, Serialize};
use serde_json::Result;

#[derive(Debug, Serialize, Deserialize)]
struct Address {
    city: String,
    street: String,
}

fn main() {
    test_serialize();
    test_deserialize();
}

fn test_serialize() {
    let address = Address {
        city: "Beijing".to_string(),
        street: "Haidian".to_string(),
    };
    let json = serde_json::to_string(&address).unwrap();
    println!("{}", json);
}
fn test_deserialize() {
    let json = r#"{"city":"Beijing","street":"Haidian"}"#;
    let address: Address = serde_json::from_str(json).unwrap();
    println!("{:?}", address);
}