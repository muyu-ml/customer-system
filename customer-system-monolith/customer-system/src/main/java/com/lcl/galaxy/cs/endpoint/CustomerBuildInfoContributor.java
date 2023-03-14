package com.lcl.galaxy.cs.endpoint;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Component
public class CustomerBuildInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("build", Collections.singletonMap("timestamp", new Date()));
    }
}
