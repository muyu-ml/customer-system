package com.lcl.galaxy.microservice.integration.service.servicebus.transformer.shanghai;

import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;
import com.lcl.galaxy.microservice.integration.service.servicebus.router.beijing.BeijingCustomerStaff;
import com.lcl.galaxy.microservice.integration.service.servicebus.transformer.CustomerStaffTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShanghaiCustomerStaffTransformer implements CustomerStaffTransformer<BeijingCustomerStaff> {

    @Override
    public List<PlatformCustomerStaff> transformerCustomerStaff(List<BeijingCustomerStaff> list) {
        return null;
    }
}
