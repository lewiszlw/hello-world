use serde::{Deserialize, Serialize};

#[derive(Debug, Deserialize, Serialize)]
pub struct NlsSpeechRequest {
    header: NlsSpeechReqHeader,
    payload: NlsSpeechReqPayload,
    context: NlsSpeechReqContext,
}
#[derive(Debug, Deserialize, Serialize)]
struct NlsSpeechReqHeader {
    appkey: String,
    message_id: String,
    name: String,
    namespace: String,
    task_id: String,
}
#[derive(Debug, Deserialize, Serialize)]
struct NlsSpeechReqPayload {
    format: String,
    pitch_rate: i32,
    sample_rate: i32,
    speech_rate: i32,
    text: String,
    voice: String,
    volume: i32,
}
#[derive(Debug, Deserialize, Serialize)]
struct NlsSpeechReqContext {
    sdk: SpeechReqSdk,
}
#[derive(Debug, Deserialize, Serialize)]
struct SpeechReqSdk {
    language: String,
    name: String,
    version: String,
}

impl NlsSpeechRequest {
    pub fn new(text: String) -> Self {
        let header = NlsSpeechReqHeader {
            appkey: "ZGIJ3AElLuz3LMRa".to_string(),
            message_id: generate_uuid(),
            name: "StartSynthesis".to_string(),
            namespace: "SpeechSynthesizer".to_string(),
            task_id: generate_uuid(),
        };
        let payload = NlsSpeechReqPayload {
            format: "pcm".to_string(),
            pitch_rate: 0,
            sample_rate: 8000,
            speech_rate: -100,
            text,
            voice: "aixia".to_string(),
            volume: 100,
        };
        let context = NlsSpeechReqContext {
            sdk: SpeechReqSdk {
                language: "C++".to_string(),
                name: "nls-sdk-linux".to_string(),
                version: "3.0.10".to_string(),
            },
        };
        let speech_req = NlsSpeechRequest {
            header,
            payload,
            context,
        };

        speech_req
    }

    pub fn to_json(&self) -> String {
        serde_json::to_string(&self).unwrap()
    }
}

fn generate_uuid() -> String {
    uuid::Uuid::new_v4().to_string().replace("-", "")
}