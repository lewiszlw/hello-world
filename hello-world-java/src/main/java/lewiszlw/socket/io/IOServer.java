package lewiszlw.socket.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/10/1
 * Time:00:36
 */
public class IOServer {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8000);

        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    InputStream is = socket.getInputStream();
                    new Thread(() -> {
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            while ((len=is.read(data))!=-1) {
                                System.out.println(new String(data, 0, len));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
