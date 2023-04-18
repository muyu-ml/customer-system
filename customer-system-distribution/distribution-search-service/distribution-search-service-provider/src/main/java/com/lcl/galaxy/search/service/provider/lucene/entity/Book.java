package com.lcl.galaxy.search.service.provider.lucene.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private Integer id;
    private String title;
    private String summary;
}