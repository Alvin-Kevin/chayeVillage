package com.chayevillage.service;

import com.chayevillage.entity.Banner;

import java.util.List;

public interface BannerService {

    List<Banner> getActiveList();

    List<Banner> getAllList();

    Banner getById(Long id);

    boolean save(Banner banner);

    boolean update(Banner banner);

    boolean delete(Long id);
}
