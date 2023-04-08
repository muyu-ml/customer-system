package com.lcl.galaxy.distribution.im.client.client;

import com.lcl.galaxy.distribution.im.client.handler.*;
import com.lcl.galaxy.distribution.im.common.handler.PacketCodecHandler;
import com.lcl.galaxy.distribution.im.common.handler.ServerIdelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ClientTest {
    public static void main(String[] args) {
        start();
    }

    private static String userId = "101";
    private static String userName = "lcl";

    private static String host = "127.0.0.1";
    private static int port = 8886;

    public static void start(){
        NioEventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(work).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ServerIdelHandler());
                        nioSocketChannel.pipeline().addLast(PacketCodecHandler.getInstance());
                        nioSocketChannel.pipeline().addLast(new ClientIdleHandler());
                        nioSocketChannel.pipeline().addLast(new LoginHandler(userId, userName));
                        nioSocketChannel.pipeline().addLast(LoginResponseHandler.getInstance());
                        nioSocketChannel.pipeline().addLast(MessageResponseHandler.getInstance());
                    }
                });
        ChannelFuture future = bootstrap.connect(host, port).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if(channelFuture.isSuccess()){
                    log.info("连接服务器成功");
                }else {
                    log.info("连接服务器失败");
                    channelFuture.cause().printStackTrace();
                    System.exit(0);
                }
            }
        });
        try {
            future.channel().closeFuture().sync();
            log.info("与服务器断开连接！");
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
