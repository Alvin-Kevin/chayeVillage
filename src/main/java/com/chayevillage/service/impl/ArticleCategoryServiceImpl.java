package com.chayevillage.service.impl;

import com.chayevillage.common.BusinessException;
import com.chayevillage.entity.ArticleCategory;
import com.chayevillage.mapper.ArticleCategoryMapper;
import com.chayevillage.service.ArticleCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    private final ArticleCategoryMapper articleCategoryMapper;

    @Override
    public List<ArticleCategory> getAll() {
        return articleCategoryMapper.selectList(null);
    }

    @Override
    public ArticleCategory getById(Long id) {
        ArticleCategory category = articleCategoryMapper.selectById(id);
        if (category == null) {
            throw new BusinessException(404, "文章分类不存在");
        }
        return category;
    }

    @Override
    public boolean save(ArticleCategory category) {
        return articleCategoryMapper.insert(category) > 0;
    }

    @Override
    public boolean update(ArticleCategory category) {
        getById(category.getId());
        return articleCategoryMapper.updateById(category) > 0;
    }

    @Override
    public boolean delete(Long id) {
        getById(id);
        return articleCategoryMapper.deleteById(id) > 0;
    }
}
