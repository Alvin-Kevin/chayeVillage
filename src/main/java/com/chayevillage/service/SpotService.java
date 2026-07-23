package com.chayevillage.service;

import com.chayevillage.common.PageResult;
import com.chayevillage.entity.Spot;

import java.util.List;

public interface SpotService {

    List<Spot> getActiveList();

    PageResult<Spot> getPage(int page, int size);

    Spot getById(Long id);

    boolean save(Spot spot);

    boolean update(Spot spot);

    boolean delete(Long id);

    long count();
}
