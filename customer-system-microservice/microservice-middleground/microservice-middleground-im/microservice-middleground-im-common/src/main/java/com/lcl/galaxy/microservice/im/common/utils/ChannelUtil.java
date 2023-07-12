package com.lcl.galaxy.microservice.im.common.utils;

import com.lcl.galaxy.microservice.im.common.protocol.Packet;
import com.lcl.galaxy.microservice.im.common.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class ChannelUtil {
    public static void writeAndFlush(Channel channel, Packet packet){
        ByteBuf buf = PacketCodeC.getInstance().encode(packet);
        channel.writeAndFlush(buf);
    }
    public static void writeAndFlushWithCtx(ChannelHandlerContext ctx, Packet packet){
        ByteBuf buf = PacketCodeC.getInstance().encode(packet);
        ctx.writeAndFlush(buf);
    }
}
