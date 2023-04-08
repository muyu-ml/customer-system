package com.lcl.galaxy.distribution.im.client.packet;

import com.lcl.galaxy.distribution.im.client.protocol.Command;
import com.lcl.galaxy.distribution.im.client.protocol.Packet;
import lombok.Data;

@Data
public class MessageResponsePacket extends BaseReponsePacket {
    /**
     * 响应内容
     */
    private String message;

    /**
     * 消息来源
     */
    private String fromUserId;
    private String fromUserName;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
