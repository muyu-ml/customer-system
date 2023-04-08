package com.lcl.galaxy.distribution.im.client.controller;

import com.lcl.galaxy.distribution.im.client.dto.ChatResponse;
import com.lcl.galaxy.distribution.im.client.dto.P2PChatRequest;
import com.lcl.galaxy.distribution.im.client.packet.MessageResponsePacket;
import com.lcl.galaxy.distribution.im.client.utils.ChannelUtil;
import com.lcl.galaxy.distribution.im.client.utils.Session;
import com.lcl.galaxy.distribution.im.client.utils.SessionUtil;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/p2p")
public class P2PChatController {

    @PostMapping(value = "/chat")
    public ChatResponse p2pChat(@RequestBody P2PChatRequest request) {
        ChatResponse response = new ChatResponse();
        String userId = request.getToUserId();
        Channel channel = SessionUtil.getChannelByUserId(userId);
        if (channel == null) {
            response.setCode("4001");
            response.setMsg(userId + "没有登录，无法向其发送即时消息！");
            return response;
        }

        Session session = SessionUtil.getSessionByChannel(channel);
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setFromUserName(session.getUserName());
        responsePacket.setFromUserId(request.getFromUserId());
        responsePacket.setMessage(request.getMsg());

        //向客户端写入数据
        ChannelUtil.writeAndFlush(channel,responsePacket);

        return response;
    }

}
