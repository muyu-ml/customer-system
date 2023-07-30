package com.lcl.galaxy.microservice.gateway.handler;

import com.alibaba.fastjson2.JSON;
import com.lcl.galaxy.microservice.middleground.task.infrastructure.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
@Order(-1) // 保证优先级比系统默认的异常处理器要高，确保出现异常时优先进入自定义处理流程中
public class GlobalExecptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if(response.isCommitted()){
            return Mono.error(ex);
        }

        // 转换为自定义的result
        Result<?> result;
        if(ex instanceof ResponseStatusException){
            // 处理网关自动抛出的异常
            result = responseStatusExceptionHandler(exchange, (ResponseStatusException) ex);
        } else {
            // 处理其他异常
            result = globalExceptionHandler(exchange,  ex);
        }
        return writeResult(exchange, result);
    }


    Result<?> responseStatusExceptionHandler(ServerWebExchange exchange, ResponseStatusException ex){
        ServerHttpRequest request = exchange.getRequest();
        log.error("路径：{} 发生异常，方法为：{}", request.getURI(), ex);
        return Result.error(ex.getRawStatusCode(), ex.getReason());
    }

    Result<?> globalExceptionHandler(ServerWebExchange exchange, Throwable ex){
        ServerHttpRequest request = exchange.getRequest();
        log.error("路径：{} 发生异常，方法为：{}", request.getURI(), ex);
        return Result.error(500, "网关发生异常");
    }

    public Mono<Void> writeResult(ServerWebExchange exchange, Object object) {
        // 设置 header
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        // 设置 Body
        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                return bufferFactory.wrap(JSON.toJSONBytes(object));
            } catch (Exception ex) {
                ServerHttpRequest request = exchange.getRequest();
                log.error("[writeResult][uri({}/{})发生异常:{}]", request.getURI(), request.getMethod(), ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }
}
