package lewiszlw._NettyInAction.chapter01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-02-15
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(18080);
            Socket socket = serverSocket.accept();

            BufferedReader br= new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            String req, resp;
            while ((req = br.readLine()) != null) {
                System.out.println("server req: " + req);

                resp = handleReq(req);

                // 需要加上换行符
                pw.write(resp + "\n");
                pw.flush();

                System.out.println("server resp " + resp);
            }

            br.close();
            pw.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String handleReq(String req) {
        return req + " is processing!";
    }
}
