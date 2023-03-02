package com.lcl.galaxy.cs;

import com.lcl.galaxy.cs.metrics.CustomerStaffCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerStaffCounterTests {

    @Test
    public void testFindCustomerStaffById(){
        CustomerStaffCounter.countPhoneNumber("123");
        CustomerStaffCounter.countPhoneNumber("123");
        CustomerStaffCounter.countPhoneNumber("456");
        Assertions.assertEquals(CustomerStaffCounter.getCount("123"), 2);
    }
}
