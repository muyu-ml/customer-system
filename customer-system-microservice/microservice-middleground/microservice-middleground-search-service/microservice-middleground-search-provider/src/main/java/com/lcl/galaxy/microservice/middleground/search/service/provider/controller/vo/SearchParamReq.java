package com.lcl.galaxy.microservice.middleground.search.service.provider.controller.vo;

import lombok.Data;

import java.util.List;

@Data
public class SearchParamReq {

    private String keyWord;

    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private Integer isDeleted;

    /**
     * 置顶搜索内容ids
     */
    private List<String> pinnedContentIds;

}
