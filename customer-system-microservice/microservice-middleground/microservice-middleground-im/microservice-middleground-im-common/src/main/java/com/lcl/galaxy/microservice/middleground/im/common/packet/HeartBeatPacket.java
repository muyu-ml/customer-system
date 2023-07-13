package com.lcl.galaxy.microservice.middleground.im.common.packet;

import com.lcl.galaxy.microservice.middleground.im.common.protocol.Command;
import com.lcl.galaxy.microservice.middleground.im.common.protocol.Packet;
import lombok.Data;

@Data
public class HeartBeatPacket extends Packet {

    private String msg = "heart-beat";

    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT;
    }
}
