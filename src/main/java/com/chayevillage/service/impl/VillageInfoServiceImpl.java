package com.chayevillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chayevillage.common.BusinessException;
import com.chayevillage.entity.VillageInfo;
import com.chayevillage.mapper.VillageInfoMapper;
import com.chayevillage.service.VillageInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VillageInfoServiceImpl implements VillageInfoService {

    private final VillageInfoMapper villageInfoMapper;

    @Override
    public List<VillageInfo> getAll() {
        return villageInfoMapper.selectList(null);
    }

    @Override
    public VillageInfo getBySection(String section) {
        LambdaQueryWrapper<VillageInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VillageInfo::getSection, section);
        VillageInfo info = villageInfoMapper.selectOne(wrapper);
        if (info == null) {
            throw new BusinessException(404, "村情信息不存在: " + section);
        }
        return info;
    }

    @Override
    public boolean saveOrUpdate(VillageInfo info) {
        // 先按 section 查是否已存在
        LambdaQueryWrapper<VillageInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(VillageInfo::getSection, info.getSection());
        VillageInfo existing = villageInfoMapper.selectOne(wrapper);
        if (existing != null) {
            info.setId(existing.getId());
            return villageInfoMapper.updateById(info) > 0;
        }
        return villageInfoMapper.insert(info) > 0;
    }
}
