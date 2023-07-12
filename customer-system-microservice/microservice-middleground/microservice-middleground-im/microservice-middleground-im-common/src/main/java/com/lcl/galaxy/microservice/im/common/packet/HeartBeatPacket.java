package com.lcl.galaxy.microservice.im.common.packet;

import com.lcl.galaxy.microservice.im.common.protocol.Command;
import com.lcl.galaxy.microservice.im.common.protocol.Packet;
import lombok.Data;

@Data
public class HeartBeatPacket extends Packet {

    private String msg = "heart-beat";

    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT;
    }
}
