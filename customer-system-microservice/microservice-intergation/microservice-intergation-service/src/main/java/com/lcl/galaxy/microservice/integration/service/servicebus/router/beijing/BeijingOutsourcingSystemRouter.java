package com.lcl.galaxy.microservice.integration.service.servicebus.router.beijing;

import com.lcl.galaxy.microservice.integration.service.servicebus.router.OutsourcingSystemRouter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeijingOutsourcingSystemRouter implements OutsourcingSystemRouter<BeijingCustomerStaff> {

    @Override
    public List<BeijingCustomerStaff> fetchOutsourcingCustomerStaffs(String systemUrl) {

        return null;
    }
}
