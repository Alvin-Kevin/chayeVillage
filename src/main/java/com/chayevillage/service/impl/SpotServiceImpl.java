package com.chayevillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chayevillage.common.BusinessException;
import com.chayevillage.entity.Spot;
import com.chayevillage.mapper.SpotMapper;
import com.chayevillage.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotServiceImpl implements SpotService {

    private final SpotMapper spotMapper;

    @Override
    public List<Spot> getActiveList() {
        LambdaQueryWrapper<Spot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Spot::getStatus, 1)
               .orderByAsc(Spot::getSortOrder);
        return spotMapper.selectList(wrapper);
    }

    @Override
    public Spot getById(Long id) {
        Spot spot = spotMapper.selectById(id);
        if (spot == null) {
            throw new BusinessException(404, "景点不存在");
        }
        return spot;
    }

    @Override
    public boolean save(Spot spot) {
        return spotMapper.insert(spot) > 0;
    }

    @Override
    public boolean update(Spot spot) {
        getById(spot.getId());
        return spotMapper.updateById(spot) > 0;
    }

    @Override
    public boolean delete(Long id) {
        getById(id);
        return spotMapper.deleteById(id) > 0;
    }
}
