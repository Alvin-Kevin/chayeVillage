package com.chayevillage.controller.frontend;

import com.chayevillage.common.PageResult;
import com.chayevillage.common.Result;
import com.chayevillage.dto.request.ProductQueryRequest;
import com.chayevillage.entity.Product;
import com.chayevillage.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Result<PageResult<Product>> getList(ProductQueryRequest params) {
        PageResult<Product> page = productService.getPage(params.getCategory(), params.getPage(), params.getSize());
        return Result.success(page);
    }

    @GetMapping("/featured")
    public Result<List<Product>> getFeatured() {
        List<Product> list = productService.getFeatured();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Product> getDetail(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.success(product);
    }
}
