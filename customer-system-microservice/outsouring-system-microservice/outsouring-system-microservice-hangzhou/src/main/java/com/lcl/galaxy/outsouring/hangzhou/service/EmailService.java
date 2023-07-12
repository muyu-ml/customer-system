package com.lcl.galaxy.outsouring.hangzhou.service;

import com.lcl.galaxy.outsouring.hangzhou.event.CustomerStaffSyncEvent;

public interface EmailService {
    void placecustomerStaffSyncNotice(CustomerStaffSyncEvent event);
}
