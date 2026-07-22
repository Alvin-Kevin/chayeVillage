package com.chayevillage.controller.admin;

import com.chayevillage.common.PageResult;
import com.chayevillage.common.Result;
import com.chayevillage.dto.request.ProductQueryRequest;
import com.chayevillage.entity.Product;
import com.chayevillage.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    @GetMapping
    public Result<PageResult<Product>> getList(ProductQueryRequest params) {
        PageResult<Product> page = productService.getPage(params.getCategory(), params.getPage(), params.getSize());
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.success(product);
    }

    @PostMapping
    public Result<?> save(@RequestBody Product product) {
        productService.save(product);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        productService.update(product);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        productService.delete(id);
        return Result.success();
    }

    @PutMapping("/{id}/status")
    public Result<?> toggleStatus(@PathVariable Long id) {
        productService.toggleStatus(id);
        return Result.success();
    }
}
