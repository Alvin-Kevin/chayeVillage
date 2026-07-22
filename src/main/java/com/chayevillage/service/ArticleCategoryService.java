package com.chayevillage.service;

import com.chayevillage.entity.ArticleCategory;

import java.util.List;

public interface ArticleCategoryService {

    List<ArticleCategory> getAll();

    ArticleCategory getById(Long id);

    boolean save(ArticleCategory category);

    boolean update(ArticleCategory category);

    boolean delete(Long id);
}
