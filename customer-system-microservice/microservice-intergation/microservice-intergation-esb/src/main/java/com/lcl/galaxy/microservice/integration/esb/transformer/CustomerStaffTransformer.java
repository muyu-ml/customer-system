package com.lcl.galaxy.microservice.integration.esb.transformer;

import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;

import java.util.List;

public interface CustomerStaffTransformer<T> {
    List<PlatformCustomerStaff> transformerCustomerStaff(List<T> list);
}
