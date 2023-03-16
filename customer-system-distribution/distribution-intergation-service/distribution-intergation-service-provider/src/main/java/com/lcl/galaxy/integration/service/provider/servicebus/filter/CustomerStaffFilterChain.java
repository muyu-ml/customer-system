package com.lcl.galaxy.integration.service.provider.servicebus.filter;

import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;

public class CustomerStaffFilterChain {

    private CustomerStaffFilter chain;

    public void addFilter(CustomerStaffFilter filter) {
        if(chain == null) {
            chain = filter;
        }else {
            chain.getLast().setNext(chain);
        }
    }

    public PlatformCustomerStaff execute(PlatformCustomerStaff customerStaff){
        if(chain != null){
            return chain.execute(customerStaff);
        }else {
            return null;
        }
    }
}
