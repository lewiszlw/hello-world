package lewiszlw._NettyInAction.chapter08;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Desc: 从channel引导客户端
 * 通过将已被接受的子 Channel 的 EventLoop 传递给 Bootstrap 的 group()方法来共享该 EventLoop。
 * 因为分配给 EventLoop 的所有 Channel 都使用同一 个线程，所以这避免了额外的线程创建，以及前面所提到的相关的上下文切换。
 *
 * @author zhanglinwei02
 * @date 2019-04-04
 */
public class BootstrapFromChannel {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(
                        new SimpleChannelInboundHandler<ByteBuf>() {

                            ChannelFuture connectFuture;

                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                // 引导客户端
                                Bootstrap bootstrap = new Bootstrap();
                                bootstrap.channel(NioSocketChannel.class)
                                        .handler(
                                                new SimpleChannelInboundHandler<ByteBuf>() {
                                                    @Override
                                                    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                                                        System.out.println("Received data");
                                                    }
                                                }
                                        );
                                // 共享 子channel 的 EventLoop
                                bootstrap.group(ctx.channel().eventLoop());
                                connectFuture = bootstrap.connect(new InetSocketAddress("http://lewiszlw.tech", 80));
                            }

                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
                                if (connectFuture.isDone()) {
                                    // do something with data
                                }
                            }
                        }
                );
        ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8080));
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("Server bound");
                } else {
                    System.err.println("Bind attempt failed");
                    future.cause().printStackTrace();
                }
            }
        });
    }
}
