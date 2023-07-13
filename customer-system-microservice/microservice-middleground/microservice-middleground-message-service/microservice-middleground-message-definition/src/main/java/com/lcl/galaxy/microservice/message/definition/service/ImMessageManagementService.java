package com.lcl.galaxy.microservice.message.definition.service;


import com.lcl.galaxy.microservice.message.definition.domain.ImMessageDTO;

import java.util.List;

public interface ImMessageManagementService {

    Long saveImMessage(ImMessageDTO imMessageDTO);

    List<ImMessageDTO> findImMessages();

    List<ImMessageDTO> findImMessagesByUserId(Long userId);
}
