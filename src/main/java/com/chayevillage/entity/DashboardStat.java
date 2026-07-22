package com.chayevillage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("dashboard_stats")
public class DashboardStat {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String statKey;

    private String statLabel;

    private String statValue;

    private String statUnit;

    private String chartType;

    private String chartData;

    private LocalDateTime updatedAt;
}
