package com.lcl.galaxy.microservice.frontend.business.client;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ToString
@Accessors(chain = true)
public class TccRequest<T> implements Serializable {
    private String xid;
    private Long branchId;
    private T data;
}