package com.chayevillage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("spots")
public class Spot {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String summary;

    private String description;

    private String coverImage;

    private String images;

    private String tags;

    private Integer status;

    private Integer sortOrder;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
