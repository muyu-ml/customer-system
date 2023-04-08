package com.lcl.galaxy.distribution.im.client.handler;

import com.lcl.galaxy.distribution.im.common.packet.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginHandler extends ChannelInboundHandlerAdapter {

    private String userId;
    private String userName;

    public LoginHandler(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 构建登录对象
        LoginRequestPacket loginRequestPaket = new LoginRequestPacket();
        loginRequestPaket.setUserId(userId);
        loginRequestPaket.setUserName(userName);
        log.info("用户{}登录中......", userId);
        ctx.channel().writeAndFlush(loginRequestPaket);
    }
}
