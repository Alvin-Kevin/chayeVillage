package com.chayevillage.controller.frontend;

import com.chayevillage.common.PageResult;
import com.chayevillage.common.Result;
import com.chayevillage.dto.request.ArticleQueryRequest;
import com.chayevillage.entity.Article;
import com.chayevillage.entity.ArticleCategory;
import com.chayevillage.service.ArticleCategoryService;
import com.chayevillage.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleCategoryService articleCategoryService;

    @GetMapping
    public Result<PageResult<Article>> getList(ArticleQueryRequest params) {
        PageResult<Article> page = articleService.getPage(params.getCategoryCode(), params.getPage(), params.getSize());
        return Result.success(page);
    }

    @GetMapping("/categories")
    public Result<List<ArticleCategory>> getCategories() {
        List<ArticleCategory> list = articleCategoryService.getAll();
        return Result.success(list);
    }

    @GetMapping("/pinned")
    public Result<List<Article>> getPinned() {
        List<Article> list = articleService.getPinned();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Article> getDetail(@PathVariable Long id) {
        Article article = articleService.getById(id);
        articleService.incrementViewCount(id);
        return Result.success(article);
    }
}
