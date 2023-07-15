package com.lcl.galaxy.microservice.middleground.task.infrastructure.tag;

import feign.RequestTemplate;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpHeaders;

public class TagUtils {
    private static final String TAG_NAME = "tag";

    public static String getTag(ServiceInstance serviceInstance){
        // 通过服务定义的元数据来获取标签信息
        return serviceInstance.getMetadata().get(TAG_NAME);
    }

    public static String getTag(HttpHeaders headers){
        return headers.getFirst(TAG_NAME);
    }

    public static void setTagName(RequestTemplate requestTemplate, String tag){
        requestTemplate.header(TAG_NAME, tag);
    }
}
