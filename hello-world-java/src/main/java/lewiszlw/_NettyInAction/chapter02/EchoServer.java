package lewiszlw._NettyInAction.chapter02;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-02-18
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        final EchoServerHandler echoServerHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap
                    .group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    // 添加一个 EchoServerHandler 到子 Channel 的 ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception {
                            // EchoServerHandler 声明为 Sharable，所以可以使用同样的实例
                            socketChannel.pipeline().addLast(echoServerHandler);
                        }
                    });
            // 异步绑定服务器，调用 sync() 方法阻塞直到绑定完成
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            // 获取 Channel 的 CloseFuture，并阻塞当前线程直到它完成
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        /*
        if (args.length != 1) {
            System.out.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        */
        new EchoServer(8001).start();
    }

}
