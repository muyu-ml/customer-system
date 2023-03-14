package com.lcl.galaxy.cs.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class CustomerStaffCounter {
    private static SimpleMeterRegistry registry = new SimpleMeterRegistry();
    private static CustomerCounter customerCounter = new CustomerCounter("customerStaff", "phone", registry);

    public static void countPhoneNumber(String phoneNumber){
        customerCounter.increment(phoneNumber);
    }

    public static double getCount(String phoneNumber) {
        return customerCounter.getCount(phoneNumber);
    }
}
