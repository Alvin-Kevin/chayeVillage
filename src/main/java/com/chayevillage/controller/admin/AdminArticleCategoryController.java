package com.chayevillage.controller.admin;

import com.chayevillage.common.Result;
import com.chayevillage.entity.ArticleCategory;
import com.chayevillage.service.ArticleCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/articles/categories")
@RequiredArgsConstructor
public class AdminArticleCategoryController {

    private final ArticleCategoryService articleCategoryService;

    @GetMapping
    public Result<List<ArticleCategory>> getAll() {
        List<ArticleCategory> list = articleCategoryService.getAll();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<ArticleCategory> getById(@PathVariable Long id) {
        ArticleCategory category = articleCategoryService.getById(id);
        return Result.success(category);
    }

    @PostMapping
    public Result<?> save(@RequestBody ArticleCategory category) {
        articleCategoryService.save(category);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody ArticleCategory category) {
        category.setId(id);
        articleCategoryService.update(category);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        articleCategoryService.delete(id);
        return Result.success();
    }
}
