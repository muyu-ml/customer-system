package com.lcl.galaxy.integration.service.provider.servicebus.transformer;

import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;

import java.util.List;

public interface CustomerStaffTransformer<T> {
    List<PlatformCustomerStaff> transformerCustomerStaff(List<T> list);
}
