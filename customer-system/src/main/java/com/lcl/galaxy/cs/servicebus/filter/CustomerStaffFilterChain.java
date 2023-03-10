package com.lcl.galaxy.cs.servicebus.filter;

import com.lcl.galaxy.cs.entity.staff.CustomerStaff;

public class CustomerStaffFilterChain {

    private CustomerStaffFilter chain;

    public void addFilter(CustomerStaffFilter filter) {
        if(chain == null) {
            chain = filter;
        }else {
            chain.getLast().setNext(chain);
        }
    }

    public CustomerStaff execute(CustomerStaff customerStaff){
        if(chain != null){
            return chain.execute(customerStaff);
        }else {
            return null;
        }
    }
}
