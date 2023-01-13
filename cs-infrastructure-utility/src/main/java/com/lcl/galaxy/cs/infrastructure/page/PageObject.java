package com.lcl.galaxy.cs.infrastructure.page;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@Builder
public class PageObject<T> {
    private Long total;

    private Long pageIndex;

    private Long pageSize;

    private List<T> list;
}
