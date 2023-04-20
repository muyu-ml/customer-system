package com.lcl.galaxy.search.service.provider.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "elasticsearch.info")
public class EsInfoConfig {
    private String username;

    private String password;

    private String hostname;

    private int port;

    private String scheme;
}
