package com.lcl.galaxy.customer.service.provider.event;

import com.lcl.galaxy.customer.service.provider.entity.staff.CustomerStaff;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerStaffChangedEventProducer {

    private final String TOPIC_STAFF = "topic_staff";

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
        rocketMQTemplate.convertAndSend(TOPIC_STAFF, event);
    }
}
