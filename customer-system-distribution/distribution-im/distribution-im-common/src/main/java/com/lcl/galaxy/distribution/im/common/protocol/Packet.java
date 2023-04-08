package com.lcl.galaxy.distribution.im.common.protocol;

import lombok.Data;

@Data
public abstract class Packet {
    private Byte version = 1;
    public abstract Byte getCommand();
}
