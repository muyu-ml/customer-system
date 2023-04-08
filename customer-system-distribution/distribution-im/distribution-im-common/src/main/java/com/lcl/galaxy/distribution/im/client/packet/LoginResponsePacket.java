package com.lcl.galaxy.distribution.im.client.packet;


import com.lcl.galaxy.distribution.im.client.protocol.Command;

public class LoginResponsePacket extends BaseReponsePacket {

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
