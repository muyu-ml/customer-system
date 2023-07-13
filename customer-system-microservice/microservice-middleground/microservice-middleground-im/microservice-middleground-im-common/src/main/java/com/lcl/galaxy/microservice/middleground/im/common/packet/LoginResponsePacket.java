package com.lcl.galaxy.microservice.middleground.im.common.packet;


import com.lcl.galaxy.microservice.middleground.im.common.protocol.Command;

public class LoginResponsePacket extends BaseReponsePacket {

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
