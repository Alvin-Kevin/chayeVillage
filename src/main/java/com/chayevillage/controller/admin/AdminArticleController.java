package com.chayevillage.controller.admin;

import com.chayevillage.common.PageResult;
import com.chayevillage.common.Result;
import com.chayevillage.dto.request.ArticleQueryRequest;
import com.chayevillage.entity.Article;
import com.chayevillage.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/articles")
@RequiredArgsConstructor
public class AdminArticleController {

    private final ArticleService articleService;

    @GetMapping
    public Result<PageResult<Article>> getList(ArticleQueryRequest params) {
        PageResult<Article> page = articleService.getPage(params.getCategoryCode(), params.getPage(), params.getSize());
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Article> getById(@PathVariable Long id) {
        Article article = articleService.getById(id);
        return Result.success(article);
    }

    @PostMapping
    public Result<?> save(@RequestBody Article article) {
        articleService.save(article);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Article article) {
        article.setId(id);
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        articleService.delete(id);
        return Result.success();
    }

    @PutMapping("/{id}/publish")
    public Result<?> publish(@PathVariable Long id) {
        articleService.publish(id);
        return Result.success();
    }

    @PutMapping("/{id}/unpublish")
    public Result<?> unpublish(@PathVariable Long id) {
        articleService.unpublish(id);
        return Result.success();
    }
}
