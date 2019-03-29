package lewiszlw._NettyInAction.chapter01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-02-15
 */
public class SocketClient {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 18080);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();

                // 需要加上换行符
                pw.write(input + "\n");
                pw.flush();

                System.out.println("client req: " + input);
                String resp = br.readLine();
                System.out.println("client resp: " + resp);

            }

            pw.close();
            br.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
