use serde::{Deserialize, Serialize};

#[derive(Debug, Deserialize, Serialize)]
pub struct NlsSpeechRequest {
    pub header: NlsSpeechReqHeader,
    pub payload: NlsSpeechReqPayload,
    pub context: NlsSpeechReqContext,
}
#[derive(Debug, Deserialize, Serialize)]
pub struct NlsSpeechReqHeader {
    pub appkey: String,
    pub message_id: String,
    pub name: String,
    pub namespace: String,
    pub task_id: String,
}
#[derive(Debug, Deserialize, Serialize)]
pub struct NlsSpeechReqPayload {
    pub format: String,
    pub pitch_rate: i32,
    pub sample_rate: i32,
    pub speech_rate: i32,
    pub text: String,
    pub voice: String,
    pub volume: i32,
}
#[derive(Debug, Deserialize, Serialize)]
pub struct NlsSpeechReqContext {
    pub sdk: SpeechReqSdk,
}
#[derive(Debug, Deserialize, Serialize)]
pub struct SpeechReqSdk {
    pub language: String,
    pub name: String,
    pub version: String,
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

#[derive(Debug, Deserialize, Serialize)]
pub struct NlsSpeechResponse {
    pub header: NlsSpeechResponseHeader,
}
#[derive(Debug, Deserialize, Serialize)]
pub struct NlsSpeechResponseHeader {
    pub message_id: String,
    pub name: String,
    pub namespace: String,
    pub task_id: String,
    pub status_text: String,
    pub status: i32,
}

impl NlsSpeechResponse {
    pub fn complete_success(message_id: String, task_id: String) -> Self {
        let speech_resp = NlsSpeechResponse {
            header: NlsSpeechResponseHeader {
                message_id,
                name: "SynthesisCompleted".to_string(),
                namespace: "SpeechSynthesizer".to_string(),
                task_id,
                status_text: "Gateway:SUCCESS:Success.".to_string(),
                status: 20000000,
            }
        };

        speech_resp
    }

    pub fn from_json(json: &str) -> Self {
        serde_json::from_str(json).unwrap()
    }
}

fn generate_uuid() -> String {
    uuid::Uuid::new_v4().to_string().replace("-", "")
}