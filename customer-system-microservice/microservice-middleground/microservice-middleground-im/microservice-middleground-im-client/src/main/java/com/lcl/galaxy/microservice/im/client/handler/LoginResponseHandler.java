package com.lcl.galaxy.microservice.im.client.handler;

import com.lcl.galaxy.microservice.im.common.packet.LoginResponsePacket;
import com.lcl.galaxy.microservice.im.common.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    private LoginResponseHandler(){}

    private static LoginResponseHandler instance = new LoginResponseHandler();

    public static LoginResponseHandler getInstance(){
        return instance;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePaket) throws Exception {
        if(loginResponsePaket.success()){
            log.info("登录成功");
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        }else {
            log.info("登录失败：{}", loginResponsePaket.getMsg());
        }
    }
}
