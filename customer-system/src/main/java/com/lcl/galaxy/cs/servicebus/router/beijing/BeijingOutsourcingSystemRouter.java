package com.lcl.galaxy.cs.servicebus.router.beijing;

import com.lcl.galaxy.cs.servicebus.router.OutsourcingSystemRouter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeijingOutsourcingSystemRouter implements OutsourcingSystemRouter<BeijingCustomerStaff> {

    @Override
    public List<BeijingCustomerStaff> fetchOutsourcingCustomerStaffs(String systemUrl) {

        return null;
    }
}
