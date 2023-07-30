package com.lcl.galaxy.microservice.frontend.ticket.event.stream;

import com.lcl.galaxy.microservice.frontend.ticket.entity.LocalCustomerStaff;
import com.lcl.galaxy.microservice.frontend.ticket.event.CustomerStaffChangedEvent;
import com.lcl.galaxy.microservice.frontend.ticket.event.CustomerStaffEventDTO;
import com.lcl.galaxy.microservice.frontend.ticket.service.ILocalCustomerStaffService;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
//@RocketMQMessageListener(consumerGroup = "consumer_group_staff_tag", topic = "topic_staff_tag", selectorExpression = "STAFF")
public class CustomerStaffTagStreamConsumer {

    @Autowired
    private ILocalCustomerStaffService localCustomerStaffService;

    @Bean
    public Consumer<CustomerStaffChangedEvent> staffChangedEventConsumer(){
        return message ->{
            System.out.println("Received message : " + message);

            CustomerStaffEventDTO dto = message.getMessage();
            LocalCustomerStaff localCustomerStaff = new LocalCustomerStaff();

            convertLocalCustomerStaff(dto, localCustomerStaff);

            String operation = message.getOperation();
            if(operation.equals("CREATE")) {
                localCustomerStaffService.insertLocalCustomerStaff(localCustomerStaff);
            } else if(operation.equals("UPDATE")) {
                localCustomerStaffService.updateLocalCustomerStaff(localCustomerStaff);
            } else if(operation.equals("DELETE")) {
                localCustomerStaffService.deleteLocalCustomerStaff(localCustomerStaff);
            }
        };
    }

    private void convertLocalCustomerStaff(CustomerStaffEventDTO dto, LocalCustomerStaff localCustomerStaff) {
        localCustomerStaff.setStaffId(dto.getId());
        localCustomerStaff.setStaffName(dto.getStaffName());
        localCustomerStaff.setAccountId(dto.getAccountId());
        localCustomerStaff.setPhone(dto.getPhone());
    }
}
