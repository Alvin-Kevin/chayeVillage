package com.chayevillage.service;

import com.chayevillage.common.PageResult;
import com.chayevillage.entity.Article;

import java.util.List;

public interface ArticleService {

    PageResult<Article> getPage(String categoryCode, int page, int size);

    Article getById(Long id);

    List<Article> getPinned();

    boolean save(Article article);

    boolean update(Article article);

    boolean delete(Long id);

    boolean publish(Long id);

    boolean unpublish(Long id);

    void incrementViewCount(Long id);
}
