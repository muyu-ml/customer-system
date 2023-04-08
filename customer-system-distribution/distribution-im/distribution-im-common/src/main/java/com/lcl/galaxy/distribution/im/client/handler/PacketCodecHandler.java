package com.lcl.galaxy.distribution.im.client.handler;

import com.lcl.galaxy.distribution.im.client.protocol.Packet;
import com.lcl.galaxy.distribution.im.client.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    private PacketCodecHandler(){}
    private static PacketCodecHandler instance = new PacketCodecHandler();

    public static PacketCodecHandler getInstance(){
        return instance;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) throws Exception {
        ByteBuf byteBuf = channelHandlerContext.channel().alloc().ioBuffer();
        PacketCodeC.getInstance().encode(byteBuf, packet);
        list.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodeC.getInstance().decode(byteBuf));
    }
}
