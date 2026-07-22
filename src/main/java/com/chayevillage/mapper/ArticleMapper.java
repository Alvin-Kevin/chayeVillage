package com.chayevillage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chayevillage.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<Article> selectPageWithCategory(Page<Article> page,
                                          @Param("categoryCode") String categoryCode,
                                          @Param("status") Integer status);
}
