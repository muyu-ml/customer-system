package com.lcl.galaxy.distribution.im.client.handler;

import com.lcl.galaxy.distribution.im.common.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    private MessageResponseHandler(){}

    private static MessageResponseHandler instance = new MessageResponseHandler();

    public static MessageResponseHandler getInstance(){
        return instance;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        log.info("收到{}的消息：{}", messageResponsePacket.getFromUserId(), messageResponsePacket.getMessage());
    }
}
