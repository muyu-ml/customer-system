package com.lcl.galaxy.distribution.im.client.packet;

import com.lcl.galaxy.distribution.im.client.protocol.Command;

public class DefaultErrorPacket extends BaseReponsePacket{

    @Override
    public Byte getCommand() {
        return Command.DEFAULT_ERROR;
    }
}
