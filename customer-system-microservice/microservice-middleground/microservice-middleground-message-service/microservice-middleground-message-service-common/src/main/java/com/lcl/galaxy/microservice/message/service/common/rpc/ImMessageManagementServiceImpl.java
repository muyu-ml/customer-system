package com.lcl.galaxy.microservice.message.service.common.rpc;

//import org.apache.dubbo.config.annotation.DubboService;

import com.lcl.galaxy.microservice.message.service.common.service.ImMessageService;
import com.lcl.galaxy.microservice.message.service.definition.domain.ImMessageDTO;
import com.lcl.galaxy.microservice.message.service.definition.service.ImMessageManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@DubboService
@Service
public class ImMessageManagementServiceImpl implements ImMessageManagementService {

    @Autowired
    private ImMessageService imMessageService;

    @Override
    public Long saveImMessage(ImMessageDTO imMessageDTO) {

//        ImMessage imMessage = ImMessageConverter.INSTANCE.convert(imMessageDTO);
//        imMessageService.saveImMessage(imMessage);
//
//        return imMessage.getId();

        return null;
    }

    @Override
    public List<ImMessageDTO> findImMessages() {

//        List<ImMessage> imMessages = imMessageService.findImMessages();
//        return ImMessageConverter.INSTANCE.convertDTOs(imMessages);

        return null;
    }

    @Override
    public List<ImMessageDTO> findImMessagesByUserId(Long userId) {

//        List<ImMessage> imMessages = imMessageService.findImMessagesByUserId(userId);
//        return ImMessageConverter.INSTANCE.convertDTOs(imMessages);

        return null;
    }
}
