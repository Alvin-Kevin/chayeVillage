package com.chayevillage.service;

import com.chayevillage.common.PageResult;
import com.chayevillage.entity.Banner;

import java.util.List;

public interface BannerService {

    List<Banner> getActiveList();

    List<Banner> getAllList();

    PageResult<Banner> getPage(int page, int size);

    Banner getById(Long id);

    boolean save(Banner banner);

    boolean update(Banner banner);

    boolean delete(Long id);

    long count();
}
