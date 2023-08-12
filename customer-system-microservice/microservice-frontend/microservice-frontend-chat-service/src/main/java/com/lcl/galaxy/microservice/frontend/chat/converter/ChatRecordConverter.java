package com.lcl.galaxy.microservice.frontend.chat.converter;

import com.lcl.galaxy.microservice.frontend.chat.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.chat.entity.ChatRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ChatRecordConverter {
    ChatRecordConverter INSTANCE = Mappers.getMapper(ChatRecordConverter.class);

    //Event->Entity
    ChatRecord convertVO(AddChatReqVO vo);
}
