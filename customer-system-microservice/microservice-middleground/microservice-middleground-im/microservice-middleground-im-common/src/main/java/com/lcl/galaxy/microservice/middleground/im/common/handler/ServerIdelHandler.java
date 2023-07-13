package com.lcl.galaxy.microservice.middleground.im.common.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerIdelHandler extends IdleStateHandler {

    private static final int HEAT_BEAT_TIME = 150;

    public ServerIdelHandler() {
        super(0, 0, HEAT_BEAT_TIME);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        log.warn("{} 时间内没有收到心跳", HEAT_BEAT_TIME);
        ctx.channel().close();
    }
}
