package com.lcl.galaxy.microservice.integration.esb.transformer.shanghai;

import com.lcl.galaxy.microservice.integration.api.dto.PlatformCustomerStaff;
import com.lcl.galaxy.microservice.integration.esb.router.beijing.BeijingCustomerStaff;
import com.lcl.galaxy.microservice.integration.esb.transformer.CustomerStaffTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShanghaiCustomerStaffTransformer implements CustomerStaffTransformer<BeijingCustomerStaff> {

    @Override
    public List<PlatformCustomerStaff> transformerCustomerStaff(List<BeijingCustomerStaff> list) {
        return null;
    }
}
