package lewiszlw.socket.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/10/1
 * Time:00:46
 */
public class IOClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8000);

        new Thread(() -> {
            try {
                while (true) {
                    socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                    Thread.sleep(2000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
