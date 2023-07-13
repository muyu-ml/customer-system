package com.lcl.galaxy.microservice.integration.esb.router;

import java.util.List;

public interface OutsourcingSystemRouter<T> {
    List<T> fetchOutsourcingCustomerStaffs(String systemUrl);
}
