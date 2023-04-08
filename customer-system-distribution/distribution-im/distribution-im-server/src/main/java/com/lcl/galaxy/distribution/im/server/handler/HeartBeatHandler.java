package com.lcl.galaxy.distribution.im.server.handler;

import com.alibaba.fastjson.JSON;
import com.lcl.galaxy.distribution.im.common.packet.HeartBeatPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeartBeatHandler extends SimpleChannelInboundHandler<HeartBeatPacket> {

    private HeartBeatHandler(){}
    private static HeartBeatHandler instance = new HeartBeatHandler();
    public static HeartBeatHandler getInstance(){
        return instance;
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatPacket heartBeatPacket) throws Exception {
        log.info("收到心跳包：{}", JSON.toJSONString(heartBeatPacket));
        channelHandlerContext.channel().writeAndFlush(heartBeatPacket);
    }
}
