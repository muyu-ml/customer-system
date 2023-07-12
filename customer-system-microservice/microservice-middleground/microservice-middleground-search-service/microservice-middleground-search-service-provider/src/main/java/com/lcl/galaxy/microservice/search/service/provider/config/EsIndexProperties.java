package com.lcl.galaxy.microservice.search.service.provider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "elasticsearch.index")
public class EsIndexProperties {
    /**
     * 客服自动回复索引
     */
    private String customerAutoReplyIndex;
}
