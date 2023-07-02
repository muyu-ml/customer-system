package com.lcl.galaxy.cs.infrastructure.event;

import lombok.Data;

@Data
public abstract class DomainEvent<T> extends BaseEvent{
    //自定义事件类型
    private String type;
    //事件所对应的操作
    private String operation;
    //事件对应的领域对象
    private T message;
}
