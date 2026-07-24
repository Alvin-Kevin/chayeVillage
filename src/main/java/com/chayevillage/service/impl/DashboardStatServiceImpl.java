package com.chayevillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chayevillage.common.BusinessException;
import com.chayevillage.entity.DashboardStat;
import com.chayevillage.mapper.DashboardStatMapper;
import com.chayevillage.service.DashboardStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardStatServiceImpl implements DashboardStatService {

    private final DashboardStatMapper dashboardStatMapper;

    @Override
    public List<DashboardStat> getAll() {
        return dashboardStatMapper.selectList(null);
    }

    @Override
    public DashboardStat getByKey(String key) {
        LambdaQueryWrapper<DashboardStat> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DashboardStat::getStatKey, key);
        DashboardStat stat = dashboardStatMapper.selectOne(wrapper);
        if (stat == null) {
            throw new BusinessException(404, "数据大屏统计项不存在: " + key);
        }
        return stat;
    }

    @Override
    public boolean saveOrUpdate(DashboardStat stat) {
        LambdaQueryWrapper<DashboardStat> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DashboardStat::getStatKey, stat.getStatKey());
        DashboardStat existing = dashboardStatMapper.selectOne(wrapper);
        if (existing != null) {
            stat.setId(existing.getId());
            return dashboardStatMapper.updateById(stat) > 0;
        }
        return dashboardStatMapper.insert(stat) > 0;
    }
}
