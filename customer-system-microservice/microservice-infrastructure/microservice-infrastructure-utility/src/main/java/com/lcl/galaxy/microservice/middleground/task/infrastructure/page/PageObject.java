package com.lcl.galaxy.microservice.middleground.task.infrastructure.page;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Accessors(chain = true)
public class PageObject<T> implements Serializable {
    private Long total;

    private Long pageIndex;

    private Long pageSize;

    private List<T> list;

    public void buildPage(List<T> list, Long total, Long pageIndex, Long pageSize) {
        this.list = list;
        this.total = total;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}
