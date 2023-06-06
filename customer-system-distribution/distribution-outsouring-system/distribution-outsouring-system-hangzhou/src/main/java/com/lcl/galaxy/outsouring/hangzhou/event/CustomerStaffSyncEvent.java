package com.lcl.galaxy.outsouring.hangzhou.event;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CustomerStaffSyncEvent implements Serializable {
    private String eventId;
    private Date eventTime;
    private String eventContent;

    public CustomerStaffSyncEvent(String eventContent){
        this.eventContent = eventContent;
        this.eventId = Distribute;
        this.eventTime = new Date();
    }
}
