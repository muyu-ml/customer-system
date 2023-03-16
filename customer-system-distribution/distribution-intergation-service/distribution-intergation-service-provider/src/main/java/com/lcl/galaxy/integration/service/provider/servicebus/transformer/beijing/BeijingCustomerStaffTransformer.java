package com.lcl.galaxy.integration.service.provider.servicebus.transformer.beijing;

import com.lcl.galaxy.integration.service.definition.domain.PlatformCustomerStaff;
import com.lcl.galaxy.integration.service.provider.servicebus.router.beijing.BeijingCustomerStaff;
import com.lcl.galaxy.integration.service.provider.servicebus.transformer.CustomerStaffTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeijingCustomerStaffTransformer implements CustomerStaffTransformer<BeijingCustomerStaff> {

    @Override
    public List<PlatformCustomerStaff> transformerCustomerStaff(List<BeijingCustomerStaff> list) {
        return null;
    }
}
