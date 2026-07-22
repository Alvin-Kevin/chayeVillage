package com.chayevillage.service;

import com.chayevillage.entity.VillageInfo;

import java.util.List;

public interface VillageInfoService {

    List<VillageInfo> getAll();

    VillageInfo getBySection(String section);

    boolean saveOrUpdate(VillageInfo info);
}
