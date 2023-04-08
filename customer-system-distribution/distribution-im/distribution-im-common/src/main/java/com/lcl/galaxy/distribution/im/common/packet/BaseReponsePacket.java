package com.lcl.galaxy.distribution.im.common.packet;

import com.lcl.galaxy.distribution.im.common.protocol.Packet;
import lombok.Data;

@Data
public abstract class BaseReponsePacket extends Packet {

    /**
     * 返回状态码，0000-成功
     */
    private String code = "0000";
    /**
     * 返回消息
     */
    private String msg;

    /**
     * 判断是否操作成功
     * @return
     */
    public boolean success(){
        return "0000".equals(code);
    }
}
