package com.lcl.galaxy.distribution.im.server.controller;

import com.lcl.galaxy.distribution.im.common.dto.ChatResponse;
import com.lcl.galaxy.distribution.im.common.dto.P2PChatRequest;
import com.lcl.galaxy.distribution.im.common.packet.MessageResponsePacket;
import com.lcl.galaxy.distribution.im.common.utils.ChannelUtil;
import com.lcl.galaxy.distribution.im.common.utils.Session;
import com.lcl.galaxy.distribution.im.common.utils.SessionUtil;
import com.lcl.galaxy.distribution.im.server.entity.ImMessage;
import com.lcl.galaxy.distribution.im.server.service.ImMessageService;
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

    @Autowired
    @Qualifier("event_delay")
    private ImMessageService imMessageService;

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

        // 在业务层执行数据持久化操作
        saveImMessage(request);

        return response;
    }

    private void saveImMessage(P2PChatRequest request) {
        ImMessage imMessage = new ImMessage();
        imMessage.setFromUserId(request.getFromUserId())
                .setFromUsername(request.getFromUserId())
                .setToUserId(request.getToUserId())
                .setToUsername(request.getToUserId())
                .setMessage(request.getMsg());

        imMessageService.saveImMessage(imMessage);
    }

}
