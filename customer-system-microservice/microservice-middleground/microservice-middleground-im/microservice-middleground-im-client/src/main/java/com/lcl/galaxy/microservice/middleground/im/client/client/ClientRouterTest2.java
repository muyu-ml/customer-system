package com.lcl.galaxy.microservice.middleground.im.client.client;

import com.lcl.galaxy.microservice.middleground.im.client.handler.ClientIdleHandler;
import com.lcl.galaxy.microservice.middleground.im.client.handler.LoginHandler;
import com.lcl.galaxy.microservice.middleground.im.client.handler.LoginResponseHandler;
import com.lcl.galaxy.microservice.middleground.im.client.handler.MessageResponseHandler;
import com.lcl.galaxy.microservice.middleground.im.common.dto.IMLoginRequest;
import com.lcl.galaxy.microservice.middleground.im.common.dto.IMLoginResponse;
import com.lcl.galaxy.microservice.middleground.im.common.dto.IMServerInfo;
import com.lcl.galaxy.microservice.middleground.im.common.handler.PacketCodecHandler;
import com.lcl.galaxy.microservice.middleground.im.common.handler.ServerIdelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class ClientRouterTest2 {
    public static void main(String[] args) {
        start();
    }

    private static String userId = "100";
    private static String userName = "lcl2";

    private static String routeHost = "127.0.0.1";
    private static int routePort = 9003;

    public static void start(){

        // 1、从Router获取ServiceInfo信息
        IMServerInfo imServerInfo = getIMServerInfoFromRouter(routeHost, routePort);
        // 2、调用Router执行登录操作
        loginRouter(imServerInfo);
        // 3、与Server建立长链接
        connectToServer(imServerInfo);
    }

    private static IMServerInfo getIMServerInfoFromRouter(String routeHost, int routePort){
        RestTemplate restTemplate = new RestTemplate();
        IMServerInfo imServerInfo = restTemplate.getForObject("http://" + routeHost + ":" + routePort + "/serverInfo/", IMServerInfo.class);
        log.info("获取服务端信息：{}", imServerInfo);
        return imServerInfo;
    }

    private static void loginRouter(IMServerInfo imServerInfo){
        RestTemplate restTemplate = new RestTemplate();
        IMLoginRequest loginRequest = new IMLoginRequest(userId, userName, imServerInfo.getHost(), imServerInfo.getNettyPort(), imServerInfo.getHttpPort());
        IMLoginResponse response = restTemplate.postForObject("http://" + routeHost + ":" + routePort + "/login/", loginRequest, IMLoginResponse.class);
        log.info("登录返回信息：{}", response);
        if(response.success()){
            log.info("登录成功");
        }else {
            log.info("登录失败：{}，程序即将退出......", response.getMsg());
            System.exit(0);
        }
    }

    private static void connectToServer(IMServerInfo imServerInfo){
        start(imServerInfo, userId, userName);
    }

    private static void start(IMServerInfo imServerInfo, String userId, String userName){
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
        ChannelFuture future = bootstrap.connect(imServerInfo.getHost(), imServerInfo.getNettyPort()).addListener(new ChannelFutureListener() {
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
