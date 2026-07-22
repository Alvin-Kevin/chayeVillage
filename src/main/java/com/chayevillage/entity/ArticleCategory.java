package com.chayevillage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("article_categories")
public class ArticleCategory {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String code;

    private Integer sortOrder;

    private LocalDateTime createdAt;
}
