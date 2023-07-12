package com.lcl.galaxy.microservice.integration.service.servicebus.filter;

public abstract class AbstractCustomerStaffFilter implements CustomerStaffFilter{

    private CustomerStaffFilter next;

    @Override
    public void setNext(CustomerStaffFilter filter) {
        this.next = filter;
    }

    @Override
    public CustomerStaffFilter getNext() {
        return this.next;
    }

    @Override
    public CustomerStaffFilter getLast(){
        CustomerStaffFilter last = this;
        while (last.getNext() != null) {
            last = last.getNext();
        }
        return last;
    }
}
