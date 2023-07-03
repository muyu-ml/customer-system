package com.lcl.galaxy.customer.service.provider.event;

import com.alibaba.fastjson.JSON;
import com.lcl.galaxy.customer.service.provider.entity.staff.CustomerStaff;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomerStaffChangedTagEventProducer {

    private final String TOPIC_STAFF = "topic_staff_tag";

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void sendCustomerStaffChangedEvent(CustomerStaff customerStaff, String operation){
        CustomerStaffEventDTO customerStaffEventDTO = new CustomerStaffEventDTO();
        customerStaffEventDTO.setId(customerStaff.getId());
        customerStaffEventDTO.setStaffName(customerStaff.getStaffName());
        customerStaffEventDTO.setAccountId(customerStaff.getAccountId());
        customerStaffEventDTO.setPhone(customerStaff.getPhone());

        CustomerStaffChangedEvent event = new CustomerStaffChangedEvent();
        event.setType("STAFF");
        event.setOperation(operation);
        event.setMessage(customerStaffEventDTO);

        log.info("发送消息：【{}】", JSON.toJSONString(event));
        String destination = String.format("%s:%s", TOPIC_STAFF, "STAFF");
        rocketMQTemplate.convertAndSend(destination, event);

        event.setType("OTHER");
        log.info("发送消息：【{}】", JSON.toJSONString(event));
        destination = String.format("%s:%s", TOPIC_STAFF, "OTHER");
        rocketMQTemplate.convertAndSend(destination, event);
    }
}
