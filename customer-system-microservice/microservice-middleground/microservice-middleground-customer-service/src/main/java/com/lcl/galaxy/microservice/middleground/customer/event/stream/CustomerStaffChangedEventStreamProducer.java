package com.lcl.galaxy.microservice.middleground.customer.event.stream;

import com.lcl.galaxy.microservice.middleground.customer.entity.staff.CustomerStaff;
import com.lcl.galaxy.microservice.middleground.customer.event.CustomerStaffChangedEvent;
import com.lcl.galaxy.microservice.middleground.customer.event.CustomerStaffEventDTO;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class CustomerStaffChangedEventStreamProducer {

    @Autowired
    private StreamBridge streamBridge;

    private static String CLUSTER_MESSAGE_OUTPUT = "customer-staff-change-stream-out";

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

        Message<CustomerStaffChangedEvent> message = new GenericMessage<>(event);

        streamBridge.send(CLUSTER_MESSAGE_OUTPUT, message);
    }
}
