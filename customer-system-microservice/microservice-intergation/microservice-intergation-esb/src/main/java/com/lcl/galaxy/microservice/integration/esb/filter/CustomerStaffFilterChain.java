package com.lcl.galaxy.microservice.integration.esb.filter;

import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;

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
