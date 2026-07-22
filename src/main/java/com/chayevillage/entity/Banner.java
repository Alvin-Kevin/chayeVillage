package com.chayevillage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("banners")
public class Banner {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String subtitle;

    private String imageUrl;

    private String linkUrl;

    private Integer sortOrder;

    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
