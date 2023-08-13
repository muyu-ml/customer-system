package com.lcl.galaxy.microservice.frontend.business.service;


import com.alibaba.fastjson2.JSON;
import com.lcl.galaxy.microservice.frontend.business.action.CreateChatTccAction;
import com.lcl.galaxy.microservice.frontend.business.action.CreateTicketTccAction;
import com.lcl.galaxy.microservice.frontend.business.client.ChatClient;
import com.lcl.galaxy.microservice.frontend.business.client.TicketClient;
import com.lcl.galaxy.microservice.frontend.business.controller.vo.AddChatReqVO;
import com.lcl.galaxy.microservice.frontend.business.controller.vo.AddTicketReqVO;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.id.DistributedId;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {
    @Autowired
    private CreateChatTccAction chatTccAction;
    @Autowired
    private CreateTicketTccAction ticketTccAction;


    @GlobalTransactional
    public void initializeCustomerAndTicket(Long userId, Long staffId, String inquire) {
        String ticetNo = DistributedId.getInstance().getFastSimpleUUID();
        AddTicketReqVO addTicketReqVO = new AddTicketReqVO();
        addTicketReqVO.setUserId(userId);
        addTicketReqVO.setStaffId(staffId);
        addTicketReqVO.setInquire(inquire);
        addTicketReqVO.setTicketNo(ticetNo);
        ticketTccAction.prepare(null, JSON.toJSONString(addTicketReqVO));

        AddChatReqVO addChatReqVO = AddChatReqVO.builder().userId(userId)
                .staffId(staffId).inquire(inquire).ticketNo(ticetNo).build();
        chatTccAction.prepare(null, JSON.toJSONString(addChatReqVO));

    }

}
