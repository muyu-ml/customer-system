package com.lcl.galaxy.microservice.im.common.packet;

import com.lcl.galaxy.microservice.im.common.protocol.Command;

public class DefaultErrorPacket extends BaseReponsePacket{

    @Override
    public Byte getCommand() {
        return Command.DEFAULT_ERROR;
    }
}
