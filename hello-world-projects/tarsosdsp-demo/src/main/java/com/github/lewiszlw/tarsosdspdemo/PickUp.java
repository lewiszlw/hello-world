package com.github.lewiszlw.tarsosdspdemo;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.SilenceDetector;
import be.tarsos.dsp.io.TarsosDSPAudioFloatConverter;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.UniversalAudioInputStream;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PickUp {

    private ConcurrentLinkedQueue<byte[]> audioQueue = new ConcurrentLinkedQueue<byte[]>();
    private boolean isFinishReadFile = false; // 是否读取完文件
    private String filePath;
    private String fileName;
    private int readLength = 1600; // 100ms音频的字节数
    private int noinputTimeout = 1000; //跳过开始多少ms
    private int silenceMaxTimes = 10; // 以100ms为单位 检测连续的多少次静音
    private float sampleRate = 8000; // 采样率
    private int sampleSizeInBits = 16; //位深度

    /**
     * 用户接听检测
     * @param filePath 文件路径
     * @param sampleRate 采样率
     * @param sampleSizeInBits 位深度
     * @param noinputTimeout 需要跳过多久时长开始检测
     * @param silenceTimeout 默认沉默多久结束(兜底)
     *
     * @date 检测方式:1.嘟嘟嘟采用450HZ的频率检测 2.彩铃采用连续活跃进行检测
     */
    public PickUp(String filePath, float sampleRate, int sampleSizeInBits, int noinputTimeout, int silenceTimeout) {
        this.filePath = filePath;
        this.sampleRate = sampleRate;
        this.sampleSizeInBits = sampleSizeInBits;
        //根据参数计算100ms音频的字节数
        this.readLength = (int)sampleRate*(sampleSizeInBits/8)/10;
        this.noinputTimeout = noinputTimeout;
        //计算检测几个 100毫秒单位长度
        this.silenceMaxTimes = (int)silenceTimeout/100;
    }

    public void start() {
        File audioFile = new File(this.filePath);
        FileInputStream fis;
        try {
            audioQueue.clear();
            fileName = audioFile.getName();
            isFinishReadFile = false;
            Thread sttThread = new Thread(vadRunbale);
            sttThread.start();
            fis = new FileInputStream(audioFile);
            byte[] byteArr = new byte[this.readLength];

            int size;
            fis.skip(44);
            while ((size = fis.read(byteArr)) != -1) {
                audioQueue.add(byteArr.clone());
            }

            while (!audioQueue.isEmpty() && !isFinishReadFile) {
                Thread.sleep(2000);
            }
            isFinishReadFile = true;
            fis.close();
            while (sttThread.isAlive()) {
                Thread.sleep(2000);
            }
            //在这里回调
            System.out.println("正常结束");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private Runnable vadRunbale = new Runnable() {
        volatile int countHZ = 0;
        volatile int count450HZ = 0;
        @Override
        public void run() {
            RingbackType ringbackType = RingbackType.UNCHECK;
            int currentPartTime = 0, silenceTimes = 0, firstActiveTimes = 0, differentCount = 0;
            try {
                // 使用tarsos检测静音
                TarsosDSPAudioFormat tdspFormat = new TarsosDSPAudioFormat(sampleRate, sampleSizeInBits, 1, true, false);
                float[] voiceFloatArr = new float[readLength / tdspFormat.getFrameSize()];

                while (!isFinishReadFile) {// 条件是主动结束,并且队列中已经没有数据
                    byte[] data = audioQueue.poll();
                    if (data == null) {
                        Thread.sleep(50);
                        continue;
                    }
                    TarsosDSPAudioFloatConverter.getConverter(tdspFormat).toFloatArray(data.clone(),voiceFloatArr);
                    SilenceDetector silenceDetector = new SilenceDetector();
                    boolean isSlience = silenceDetector.isSilence(voiceFloatArr);


                    //以100ms为单位多次检测静音
                    if ((currentPartTime+=100) >= noinputTimeout) {
                        boolean checkHZ = false;
                        if (isSlience) {
                            if(firstActiveTimes == 0){
                                System.out.println("活动前静音,忽略");
                                continue;
                            }
                            System.out.println("检测到静音"+ringbackType);
                            // 检测连续静音到达最大值 结束
                            if(++silenceTimes >=silenceMaxTimes){
                                isFinishReadFile = true;//检测到静音就不需要等待文件读取完成
                            }
                            switch(ringbackType){
                                case UNCHECK:
                                    if(countHZ==count450HZ){
                                        if(countHZ<=11){
                                            ringbackType = RingbackType.DU_NORMALITY;
                                            //中国标准为嘟1s 停4s
                                            silenceMaxTimes = 41;
                                        }else {
                                            ringbackType = RingbackType.DU_OTHER;
                                            checkHZ = true;
                                        }
                                    }
                                    break;
                                case DU_OTHER:
                                    checkHZ = true;
                                    //连续3个打破特征跳出
                                    if(countHZ!=count450HZ){
                                        differentCount++;
                                        count450HZ = countHZ;
                                    }else {
                                        differentCount = 0;
                                    }
                                    if(differentCount>=3){
                                        isFinishReadFile = true;
                                    }
                                    //嘟声启动hz检查
                                    checkHZ = true;
                                    break;
                                case SONG:
                                    //持续音乐中断
                                    isFinishReadFile = true;
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            System.out.println("活动状态"+ringbackType);
                            switch(ringbackType){
                                case UNCHECK:
                                    firstActiveTimes++;
                                    //首次活跃大于两秒,判定为音乐
                                    if(firstActiveTimes>=20){
                                        ringbackType = RingbackType.SONG;
                                    }
                                    //首次活跃开始启动HZ检查
                                    checkHZ = true;
                                    break;
                                case DU_NORMALITY:
                                    //沉默时长小于40
                                    if(silenceTimes!=0 &&silenceTimes<35){
                                        isFinishReadFile = true;
                                    }
                                    //不break继续执行
                                case DU_OTHER:
                                    //连续3个打破特征跳出
                                    if(countHZ!=count450HZ){
                                        differentCount++;
                                        count450HZ = countHZ;
                                    }else {
                                        differentCount = 0;
                                    }
                                    if(differentCount>=3){
                                        isFinishReadFile = true;
                                    }
                                    //嘟声启动hz检查
                                    checkHZ = true;
                                    break;
                                default:
                                    break;
                            }
                            //重置静音次数
                            silenceTimes = 0;
                        }

                        //做HZ检查
                        if(checkHZ && !isFinishReadFile){
                            //做HZ判断
                            AudioDispatcher dispatcher = new AudioDispatcher(new UniversalAudioInputStream(new ByteArrayInputStream(data), tdspFormat), data.length, 0);
                            AudioProcessor audioProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 8000, data.length, new PitchDetectionHandler(){
                                @Override
                                public void handlePitch(PitchDetectionResult pitchDetectionResult, AudioEvent audioEvent) {
                                    countHZ++;
                                    float pitch = pitchDetectionResult.getPitch();
                                    System.out.println(pitch+"HZ");
                                    if(pitch>445&&pitch<455){
                                        count450HZ++;
                                    }
                                }
                            });
                            dispatcher.addAudioProcessor(audioProcessor);
                            dispatcher.run();
                        }

                    }
                }

                System.out.println(fileName+"退出,位置为"+currentPartTime/10+"     "+ringbackType);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) {
        PickUp pickUp = new PickUp(
                "./audios/b948f04c1d66417986f929fd397e4fab-c3210beb0e6546fcb384f2aebf3569b6-1644220377014.wav",
                8000, 16, 1000, 4500);
        pickUp.start();
    }
}
