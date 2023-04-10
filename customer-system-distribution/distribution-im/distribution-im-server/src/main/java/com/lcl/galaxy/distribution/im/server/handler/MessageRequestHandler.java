package com.lcl.galaxy.distribution.im.server.handler;

import com.lcl.galaxy.distribution.im.common.packet.DefaultErrorPacket;
import com.lcl.galaxy.distribution.im.common.packet.MessageRequestPacket;
import com.lcl.galaxy.distribution.im.common.packet.MessageResponsePacket;
import com.lcl.galaxy.distribution.im.common.utils.LoginUtil;
import com.lcl.galaxy.distribution.im.common.utils.Session;
import com.lcl.galaxy.distribution.im.common.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@ChannelHandler.Sharable
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    private MessageRequestHandler(){}

    private static MessageRequestHandler instance = new MessageRequestHandler();

    public static MessageRequestHandler getInstance(){
        return instance;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        log.info("收到来自客户端的消息： {}", messageRequestPacket.getMessage());
        // 获取Session
        Session session = SessionUtil.getSessionByChannel(channelHandlerContext.channel());
        String fromUserId = session.getUserId();
        String frromUserName = session.getUserName();

        // 构造响应体
        MessageResponsePacket response = new MessageResponsePacket();
        response.setMessage(messageRequestPacket.getMessage());
        response.setFromUserId(fromUserId);
        response.setFromUserName(frromUserName);

        // 发送聊天消息
        p2pChat(channelHandlerContext, messageRequestPacket.getToUserId(), response);
    }

    private void p2pChat(ChannelHandlerContext channelHandlerContext, String toUserId, MessageResponsePacket response) {
        Channel channel = SessionUtil.getChannelByUserId(toUserId);
        if(channel != null && LoginUtil.hasLgin(channel)){
            channel.writeAndFlush(response);
        }else {
            DefaultErrorPacket defaultErrorPacket = new DefaultErrorPacket();
            defaultErrorPacket.setCode("3001");
            defaultErrorPacket.setMsg("该用户没有登录，无法发送消息");
            channel.writeAndFlush(defaultErrorPacket);
        }
    }
}
