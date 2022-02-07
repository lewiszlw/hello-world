package com.github.lewiszlw.tarsosdspdemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class FileInputStreamDemo {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    public static void main(String[] args) {
        executorService.submit(() -> {
            FileOutputStream fos = null;
            File file = new File("./fis-test");
            try {
                System.out.println("1");
                if (!file.exists()) {
                    file.createNewFile();
                }
                fos = new FileOutputStream(file);
                byte[] data = new byte[100];
                for (int i = 0; i < data.length; i++) {
                    data[i] = (byte) ThreadLocalRandom.current().nextInt(10);
                }
                while (true) {
                    fos.write(data);
                    System.out.println("已写入100bytes");
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.submit(() -> {
            FileInputStream fis = null;
            try {
                File file = new File("./fis-test");
                if (!file.exists()) {
                    file.createNewFile();
                }
                fis = new FileInputStream(file);
                byte[] data = new byte[1000];
                while (true) {
                    int available = fis.available();
                    if (available >= 1000) {
                        fis.read(data);
                        System.out.println("已读取1000bytes");
                    } else {
                        System.out.println("文件可读bytes没有达到1000, " + available);
                    }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
