# mod_unimrcp梳理

https://github.com/freeswitch/mod_unimrcp

模块初始化
1. 通过宏SWITCH_MODULE_DEFINITION定义freeswitch模块，以及模块接口（如mod_unimrcp_load函数负责加载模块）
2. 执行SWITCH_MODULE_LOAD_FUNCTION(mod_unimrcp_load)宏定义函数，来加载模块
   1. 初始化globals结构体（全局配置和变量）
   2. 调用mod_unimrcp_do_config来解析xml配置 unimrcp.conf.xml
   3. 链接unimrcp日志到freeswitch
   4. 调用mod_unimrcp_client_create函数创建mrcp_client并赋给globals结构体内字段
   5. 执行synth_load函数加载tts
      1. 将tts相关函数链接到freeswitch tts接口、dispatcher接口和音频流接口
   6. 执行recog_load函数加载asr
      1. 将asr相关函数链接到freeswitch asr接口、dispatcher接口和音频流接口
   7. 启动mrcp_client

TTS
1. freeswitch 调用 synth_speech_open 函数开启tts
   1. 命名channel TTS-%d
   2. 调用 speech_channel_create 函数创建channel
      1. 分配内存、channel state 为 closed
   3. 调用 speech_channel_open 函数打开channel
      1. 创建unimrcp session、audio termination、unimrcp channel
      2. 建立与 unimrcp server 的连接，channel state 为 ready
   4. 调用 speech_channel_set_param 函数设置channel参数（如Voice-Name）
2. freeswitch 调用 synth_speech_feed_tts 函数发送 Speak 请求给 unimrcp server
   1. 调用 synth_channel_speak 函数发送 speak 请求
      1. 创建 speak 请求消息，设置请求header
      2. 调用 synth_channel_set_params 设置参数（如vendor-specific参数）
      3. 清空 audio queue
      4. 发送 speak 请求，channel state 为 processing
3. tts 通过 dispatcher 来接收所有来自 unimrcp server 的消息
   1. 如果是 MRCP_APP_MESSAGE_TYPE_SIGNALING 消息
      1. 根据 sig message 类型，调用 dispatcher on_session_update/on_session_terminate/on_channel_add/on_channel_remove 等方法
   2. 如果是 MRCP_APP_MESSAGE_TYPE_CONTROL 消息，调用 dispatcher on_message_receive 方法（即 synth_on_message_receive 函数）
      1. 当消息类型为响应 SYNTHESIZER_SPEAK，消息状态为 MRCP_REQUEST_STATE_INPROGRESS，设置 channel state 为 processing
      2. 当消息类型为响应 SYNTHESIZER_STOP，消息状态为 MRCP_REQUEST_STATE_COMPLETE，设置 channel state 为 done
      3. 当消息类型为事件 SYNTHESIZER_SPEAK_COMPLETE，设置 channel state 为 done
      4. 否则设置 channel state 为 error
4. 调用 synth_stream_write 函数来写入tts数据
   1. 调用 speech_channel_write 函数，检查 channel state 是否为 processing，调用 audio_queue_write 函数将tts数据写入到 audio queue
5. freeswitch 调用 synth_speech_read_tts 函数读取tts数据
   1. 调用 speech_channel_read 函数（通过 audio_queue_read 函数）读取tts数据成功，如果读取tts数据长度不足预期，填充静音
   2. 调用 speech_channel_read 函数（通过 audio_queue_read 函数）读取tts数据失败，设置 channel state 为 ready（用于准备下一次 speak 请求）
6. freeswitch 调用 synth_speech_flush_tts 函数停止tts
   1. 调用 speech_channel_stop 函数，如果 channel state 为 processing，则发送 SYNTHESIZER_STOP 消息给 unimrcp server 关闭
7. freeswitch 调用 synth_speech_close 函数关闭tts
   1. 同synth_speech_flush_tts过程，调用 speech_channel_stop 函数
   2. 调用 speech_channel_destroy 函数
      1. 如果 channel state 不是 closed 状态，则结束 unimrcp session
      2. 调用 audio_queue_destroy 函数销毁 audio queue
      3. 释放内存资源

ASR
1. freeswitch 调用 recog_asr_open 函数开启asr
   1. 命名channel ASR-%d
   2. 调用 speech_channel_create 函数创建channel
      1. 分配内存、channel state 为 closed
   3. 调用 speech_channel_open 函数打开channel
      1. 创建unimrcp session、audio termination、unimrcp channel
      2. 建立与 unimrcp server 的连接，channel state 为 ready
   4. 调用 speech_channel_set_param 函数设置channel参数
2. freeswitch 调用 recog_asr_load_grammar 函数加载 grammar
   1. 调用 speech_channel_stop 函数，如果 channel state 为 processing，则发送 RECOGNIZER_STOP 消息给 unimrcp server 关闭
   2. 读取 grammar 数据
   3. 调用 recog_channel_load_grammar 函数加载 grammar，发送 RECOGNIZER_DEFINE_GRAMMAR 请求给 unimrcp server，channel state 为 processing，发送完毕后 channel state 为 ready
   4. 调用 recog_channel_disable_all_grammars 函数禁用所有 grammar
   5. 调用 recog_channel_enable_grammar 函数开启此 grammar
   6. 调用 recog_channel_start 开始发送 RECOGNIZE 请求
      1. 创建 RECOGNIZE 请求消息，设置请求header，如content-type
      2. 调用 recog_channel_set_params 设置参数（如vendor-specific参数）
      3. 设置消息 body 为 grammar 内容
      4. 清空 audio queue
      5. 发送 RECOGNIZE 请求，channel state 为 processing
3. freeswitch 调用 recog_asr_start_input_timers 函数来启动定时器设置
   1. 调用 recog_channel_start_input_timers 函数发送 RECOGNIZER_START_INPUT_TIMERS 请求
4. freeswitch 调用 recog_asr_feed 函数写入音频
   1. 调用 synth_channel_write 函数（通过 audio_queue_write 函数）写入audio queue
5. freeswitch 调用 recog_asr_feed_dtmf 函数写入dtmf
   1. 如果 dtmf_generator 不存在，则通过 mpf_dtmf_generator_create 函数创建
   2. 将 digits 写入 dtmf_generator
6. freeswitch 调用 recog_asr_check_results 函数检查asr识别结果是否完成
   1. 调用 recog_channel_check_results 函数检查识别结果是否完成
7. freeswitch 调用 recog_asr_get_results 函数获取asr识别结果（xml）
   1. 调用 recog_channel_get_results 函数返回识别结果
8. unimrcp 回调请求通过 recog_stream_read 函数读取asr音频下一帧
   1. 调用 speech_channel_read 函数（通过 audio_queue_read 函数）读取下一帧
   2. 通过 dtmf_generator 在音频帧中传输 dtmf
9. asr 通过 dispatcher 来接收所有来自 unimrcp server 的消息
   1. 如果是 MRCP_APP_MESSAGE_TYPE_SIGNALING 消息
      1. 根据 sig message 类型，调用 dispatcher on_session_update/on_session_terminate/on_channel_add/on_channel_remove 等方法
   2. 如果是 MRCP_APP_MESSAGE_TYPE_CONTROL 消息，调用 dispatcher on_message_receive 方法（即 recog_on_message_receive 函数）
      1. 当消息类型为响应 RECOGNIZER_RECOGNIZE
         1. 消息状态为 MRCP_REQUEST_STATE_INPROGRESS，设置 channel state 为 processing
         2. 消息状态为 MRCP_REQUEST_STATE_COMPLETE，设置 channel state 为 error
         3. 消息状态为 MRCP_REQUEST_STATE_PENDING，不作任何处理
      2. 当消息类型为响应 RECOGNIZER_STOP，消息状态为 MRCP_REQUEST_STATE_COMPLETE，设置 channel state 为 ready
      3. 当消息类型为响应 RECOGNIZER_START_INPUT_TIMERS，消息状态为 MRCP_REQUEST_STATE_COMPLETE，如果状态码为2xx，设置channel timers_started 标志位为 true
      4. 当消息类型为响应 RECOGNIZER_DEFINE_GRAMMAR，消息状态为 MRCP_REQUEST_STATE_COMPLETE，如果状态码为2xx，设置 channel state 为 ready
      5. 当消息类型为事件 RECOGNIZER_RECOGNITION_COMPLETE
         1. 调用 recog_channel_set_results 函数设置asr识别结果，或者当识别结果为空，设置 completion cause 为识别结果
         2. 设置 channel state 为 ready
      6. 当消息类型为事件 RECOGNIZER_START_OF_INPUT，调用 recog_channel_set_start_of_input 函数设置 start_of_input 标志位
      7. 否则设置 channel state 为 error

问题：
1. mod_unimrcp如何处理unimrcp server返回的错误状态码？ 
   - 如果响应不是正常的 complete，会将 channel state 设置为 error，并返回给freeswitch SWITCH_STATUS_FALSE 或者 false，freeswitch内部如何处理待确认。
2. 当mod_unimrcp与unimrcp server连接中断，会发生什么？
   - 连接中断后，mrcp请求响应无法发出和接收，mod_unimrcp会报错，channel state不会进入下一状态，但并不会关闭session和channel。mod_unimrcp有on_session_terminate和on_channel_remove函数，但是看起来针对的是正常关闭，异常关闭没有发现资源有释放，待进一步确认。

