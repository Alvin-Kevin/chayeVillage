package com.chayevillage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("village_info")
public class VillageInfo {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String section;

    private String title;

    private String content;

    private String imageUrl;

    private LocalDateTime updatedAt;
}
