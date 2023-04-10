package com.lcl.galaxy.distribution.im.server.handler;

import com.lcl.galaxy.distribution.im.common.packet.LoginRequestPacket;
import com.lcl.galaxy.distribution.im.common.packet.LoginResponsePacket;
import com.lcl.galaxy.distribution.im.common.utils.LoginUtil;
import com.lcl.galaxy.distribution.im.common.utils.Session;
import com.lcl.galaxy.distribution.im.common.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@ChannelHandler.Sharable
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    private LoginRequestHandler(){}
    private static LoginRequestHandler instance = new LoginRequestHandler();

    public static LoginRequestHandler getInstance(){
        return instance;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPaket) throws Exception {
        LoginResponsePacket responsePaket = login(channelHandlerContext, loginRequestPaket);
        channelHandlerContext.channel().writeAndFlush(responsePaket);
    }

    private LoginResponsePacket login(ChannelHandlerContext ctx, LoginRequestPacket paket) {
        LoginResponsePacket response = new LoginResponsePacket();
        if(checkLogin(ctx, paket)){
            log.info("用户{}登录成功", paket.getUserId());
            response.setCode("0000");
            response.setMsg("登录成功");
            // 设置用户登录状态
            LoginUtil.markAsLogin(ctx.channel());
            // 绑定 Session 和 Channel 的关系
            SessionUtil.bindSession(new Session(paket.getUserId(), paket.getUserName()), ctx.channel());
        }else {
            log.info("用户{}登录失败", paket.getUserId());
            response.setCode("1001");
            response.setMsg("登录失败");
        }
        return response;
    }

    private boolean checkLogin(ChannelHandlerContext ctx, LoginRequestPacket paket) {
        return !SessionUtil.hasLogin(ctx.channel());
    }


}
