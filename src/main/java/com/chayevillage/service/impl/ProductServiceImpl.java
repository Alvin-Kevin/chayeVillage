package com.chayevillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chayevillage.common.BusinessException;
import com.chayevillage.common.PageResult;
import com.chayevillage.entity.Product;
import com.chayevillage.mapper.ProductMapper;
import com.chayevillage.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public PageResult<Product> getPage(String category, int pageNum, int pageSize) {
        Page<Product> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(category)) {
            wrapper.eq(Product::getCategory, category);
        }
        wrapper.orderByAsc(Product::getSortOrder)
               .orderByDesc(Product::getCreatedAt);
        productMapper.selectPage(page, wrapper);
        return PageResult.of(page);
    }

    @Override
    public Product getById(Long id) {
        Product product = productMapper.selectById(id);
        if (product == null) {
            throw new BusinessException(404, "农产品不存在");
        }
        return product;
    }

    @Override
    public List<Product> getFeatured() {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Product::getIsFeatured, 1)
               .eq(Product::getStatus, 1)
               .orderByAsc(Product::getSortOrder);
        return productMapper.selectList(wrapper);
    }

    @Override
    public boolean save(Product product) {
        return productMapper.insert(product) > 0;
    }

    @Override
    public boolean update(Product product) {
        getById(product.getId());
        return productMapper.updateById(product) > 0;
    }

    @Override
    public boolean delete(Long id) {
        getById(id);
        return productMapper.deleteById(id) > 0;
    }

    @Override
    public boolean toggleStatus(Long id) {
        Product product = getById(id);
        product.setStatus(product.getStatus() == 1 ? 0 : 1);
        return productMapper.updateById(product) > 0;
    }

    @Override
    public long count() {
        return productMapper.selectCount(null);
    }
}
