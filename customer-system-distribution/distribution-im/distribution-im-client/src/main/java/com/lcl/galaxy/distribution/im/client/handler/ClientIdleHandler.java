package com.lcl.galaxy.distribution.im.client.handler;

import com.lcl.galaxy.distribution.im.common.packet.HeartBeatPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientIdleHandler extends IdleStateHandler {

    private static final int HEAT_BEAT_TIME = 50;

    public ClientIdleHandler() {
        super(0, 0, HEAT_BEAT_TIME);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        log.warn("发送心跳");
        ctx.channel().writeAndFlush(new HeartBeatPacket());
    }
}
