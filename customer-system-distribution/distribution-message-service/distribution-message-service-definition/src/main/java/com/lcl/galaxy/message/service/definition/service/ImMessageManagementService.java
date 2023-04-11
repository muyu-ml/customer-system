package com.lcl.galaxy.message.service.definition.service;


import com.lcl.galaxy.message.service.definition.domain.ImMessageDTO;

import java.util.List;

public interface ImMessageManagementService {

    Long saveImMessage(ImMessageDTO imMessageDTO);

    List<ImMessageDTO> findImMessages();

    List<ImMessageDTO> findImMessagesByUserId(Long userId);
}
