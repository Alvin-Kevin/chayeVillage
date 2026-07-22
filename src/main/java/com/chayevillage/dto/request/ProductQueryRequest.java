package com.chayevillage.dto.request;

import lombok.Data;

@Data
public class ProductQueryRequest {
    private String category;
    private Integer page = 1;
    private Integer size = 12;
}
