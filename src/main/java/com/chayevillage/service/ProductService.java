package com.chayevillage.service;

import com.chayevillage.common.PageResult;
import com.chayevillage.entity.Product;

import java.util.List;

public interface ProductService {

    PageResult<Product> getPage(String category, int page, int size);

    Product getById(Long id);

    List<Product> getFeatured();

    boolean save(Product product);

    boolean update(Product product);

    boolean delete(Long id);

    boolean toggleStatus(Long id);
}
