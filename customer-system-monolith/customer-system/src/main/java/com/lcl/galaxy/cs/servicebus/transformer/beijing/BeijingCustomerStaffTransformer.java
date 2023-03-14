package com.lcl.galaxy.cs.servicebus.transformer.beijing;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;
import com.lcl.galaxy.cs.servicebus.router.beijing.BeijingCustomerStaff;
import com.lcl.galaxy.cs.servicebus.transformer.CustomerStaffTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeijingCustomerStaffTransformer implements CustomerStaffTransformer<BeijingCustomerStaff> {

    @Override
    public List<CustomerStaff> transformerCustomerStaff(List<BeijingCustomerStaff> list) {
        return null;
    }
}
