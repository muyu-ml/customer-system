package com.lcl.galaxy.microservice.middleground.im.common.packet;

import com.lcl.galaxy.microservice.middleground.im.common.protocol.Command;
import com.lcl.galaxy.microservice.middleground.im.common.protocol.Packet;
import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {
    /**
     * 消息内容
     */
    private String message;


    /**
     * 消息接受者
     */
    private String toUserId;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
