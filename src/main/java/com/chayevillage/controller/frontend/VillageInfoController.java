package com.chayevillage.controller.frontend;

import com.chayevillage.common.Result;
import com.chayevillage.entity.VillageInfo;
import com.chayevillage.service.VillageInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/village-info")
@RequiredArgsConstructor
public class VillageInfoController {

    private final VillageInfoService villageInfoService;

    @GetMapping
    public Result<List<VillageInfo>> getAll() {
        List<VillageInfo> list = villageInfoService.getAll();
        return Result.success(list);
    }

    @GetMapping("/{section}")
    public Result<VillageInfo> getBySection(@PathVariable String section) {
        VillageInfo info = villageInfoService.getBySection(section);
        return Result.success(info);
    }
}
