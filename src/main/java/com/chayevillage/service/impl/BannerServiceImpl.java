package com.chayevillage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chayevillage.common.BusinessException;
import com.chayevillage.entity.Banner;
import com.chayevillage.mapper.BannerMapper;
import com.chayevillage.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerMapper bannerMapper;

    @Override
    public List<Banner> getActiveList() {
        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Banner::getStatus, 1)
               .orderByAsc(Banner::getSortOrder);
        return bannerMapper.selectList(wrapper);
    }

    @Override
    public List<Banner> getAllList() {
        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Banner::getCreatedAt);
        return bannerMapper.selectList(wrapper);
    }

    @Override
    public Banner getById(Long id) {
        Banner banner = bannerMapper.selectById(id);
        if (banner == null) {
            throw new BusinessException(404, "轮播图不存在");
        }
        return banner;
    }

    @Override
    public boolean save(Banner banner) {
        return bannerMapper.insert(banner) > 0;
    }

    @Override
    public boolean update(Banner banner) {
        Banner existing = getById(banner.getId());
        return bannerMapper.updateById(banner) > 0;
    }

    @Override
    public boolean delete(Long id) {
        getById(id);
        return bannerMapper.deleteById(id) > 0;
    }
}
