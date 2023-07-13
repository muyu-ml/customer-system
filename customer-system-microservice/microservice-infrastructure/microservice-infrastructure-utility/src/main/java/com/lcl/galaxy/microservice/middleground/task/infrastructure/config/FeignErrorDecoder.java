package com.lcl.galaxy.microservice.middleground.task.infrastructure.config;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class FeignErrorDecoder extends ErrorDecoder.Default {

    @Override
    public Exception decode(String methodKey, Response response) {
        Exception exception = super.decode(methodKey, response);
        // 添加异常日志，对原有异常的返回结果进行定制化处理
        log.info(exception.getMessage(), exception);
        return exception;
    }
}
