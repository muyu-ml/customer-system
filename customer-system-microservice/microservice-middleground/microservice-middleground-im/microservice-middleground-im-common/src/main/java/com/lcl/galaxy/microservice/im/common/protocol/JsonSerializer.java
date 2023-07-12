package com.lcl.galaxy.microservice.im.common.protocol;

import com.alibaba.fastjson.JSON;

public class JsonSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializeAlgorithm.json;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deSerialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
