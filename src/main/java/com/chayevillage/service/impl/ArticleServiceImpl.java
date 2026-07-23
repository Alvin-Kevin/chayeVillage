package com.chayevillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chayevillage.common.BusinessException;
import com.chayevillage.common.PageResult;
import com.chayevillage.entity.Article;
import com.chayevillage.entity.ArticleCategory;
import com.chayevillage.mapper.ArticleCategoryMapper;
import com.chayevillage.mapper.ArticleMapper;
import com.chayevillage.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final ArticleCategoryMapper articleCategoryMapper;

    @Override
    public PageResult<Article> getPage(String categoryCode, Long categoryId, int pageNum, int pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();

        if (categoryId != null) {
            wrapper.eq(Article::getCategoryId, categoryId);
        } else if (StringUtils.hasText(categoryCode)) {
            ArticleCategory category = articleCategoryMapper.selectOne(
                    new LambdaQueryWrapper<ArticleCategory>()
                            .eq(ArticleCategory::getCode, categoryCode)
            );
            if (category != null) {
                wrapper.eq(Article::getCategoryId, category.getId());
            }
        }

        wrapper.orderByDesc(Article::getIsPinned)
               .orderByDesc(Article::getCreatedAt);
        articleMapper.selectPage(page, wrapper);
        return PageResult.of(page);
    }

    @Override
    public Article getById(Long id) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new BusinessException(404, "文章不存在");
        }
        return article;
    }

    @Override
    public List<Article> getPinned() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getIsPinned, 1)
               .eq(Article::getStatus, 1)
               .orderByDesc(Article::getCreatedAt);
        return articleMapper.selectList(wrapper);
    }

    @Override
    public boolean save(Article article) {
        return articleMapper.insert(article) > 0;
    }

    @Override
    public boolean update(Article article) {
        getById(article.getId());
        return articleMapper.updateById(article) > 0;
    }

    @Override
    public boolean delete(Long id) {
        getById(id);
        return articleMapper.deleteById(id) > 0;
    }

    @Override
    public boolean publish(Long id) {
        Article article = getById(id);
        article.setStatus(1);
        article.setPublishedAt(LocalDateTime.now());
        return articleMapper.updateById(article) > 0;
    }

    @Override
    public boolean unpublish(Long id) {
        Article article = getById(id);
        article.setStatus(0);
        return articleMapper.updateById(article) > 0;
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        Article article = getById(id);
        article.setStatus(status);
        if (status == 1) {
            article.setPublishedAt(LocalDateTime.now());
        }
        return articleMapper.updateById(article) > 0;
    }

    @Override
    public void incrementViewCount(Long id) {
        Article article = getById(id);
        article.setViewCount(article.getViewCount() != null ? article.getViewCount() + 1 : 1);
        articleMapper.updateById(article);
    }

    @Override
    public long count() {
        return articleMapper.selectCount(null);
    }
}
