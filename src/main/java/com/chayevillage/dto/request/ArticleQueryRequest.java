package com.chayevillage.dto.request;

import lombok.Data;

@Data
public class ArticleQueryRequest {
    private String categoryCode;
    private Integer page = 1;
    private Integer size = 12;
}
