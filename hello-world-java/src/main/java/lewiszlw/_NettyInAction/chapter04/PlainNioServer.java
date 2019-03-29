package lewiszlw._NettyInAction.chapter04;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Desc: 不使用netty的nio编程
 *
 * @author zhanglinwei02
 * @date 2019-02-21
 */
public class PlainNioServer {

    public void serve(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(port));

        Selector selector = Selector.open();
        // 将ServerSocketChannel注册到selector以接受连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());

        for (;;) {
            try {
                // 等待需要处理的事件，阻塞会持续到下一个传入事件
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                try {
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        // 接受客户端，并注册到selector
                        client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, msg.duplicate());
                        System.out.println("Accepted connection from " + client);
                    }
                    if (selectionKey.isWritable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                        while (buffer.hasRemaining()) {
                            // 将数据写到已连接的客户端
                            if (client.write(buffer) == 0) {
                                break;
                            }
                        }
                        client.close();
                    }
                } catch (IOException e) {
                    selectionKey.cancel();
                    try {
                        selectionKey.channel().close();
                    } catch (IOException ex) {

                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new PlainNioServer().serve(18080);
    }
}
