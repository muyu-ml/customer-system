package com.lcl.galaxy.microservice.integration.esb.router.beijing;

import com.lcl.galaxy.microservice.integration.esb.router.OutsourcingSystemRouter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeijingOutsourcingSystemRouter implements OutsourcingSystemRouter<BeijingCustomerStaff> {

    @Override
    public List<BeijingCustomerStaff> fetchOutsourcingCustomerStaffs(String systemUrl) {

        return null;
    }
}
