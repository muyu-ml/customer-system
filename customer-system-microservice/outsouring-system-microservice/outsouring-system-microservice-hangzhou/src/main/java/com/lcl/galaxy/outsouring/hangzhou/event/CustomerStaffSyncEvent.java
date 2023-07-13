package com.lcl.galaxy.outsouring.hangzhou.event;

import com.lcl.galaxy.microservice.middleground.task.infrastructure.id.DistributedId;
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
        this.eventId = DistributedId.getInstance().getFastSimpleUUID();
        this.eventTime = new Date();
    }
}
