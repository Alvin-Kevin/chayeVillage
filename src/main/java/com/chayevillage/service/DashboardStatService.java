package com.chayevillage.service;

import com.chayevillage.entity.DashboardStat;

import java.util.List;

public interface DashboardStatService {

    List<DashboardStat> getAll();

    DashboardStat getByKey(String key);

    boolean saveOrUpdate(DashboardStat stat);
}
