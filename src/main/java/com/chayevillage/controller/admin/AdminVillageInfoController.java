package com.chayevillage.controller.admin;

import com.chayevillage.common.Result;
import com.chayevillage.entity.VillageInfo;
import com.chayevillage.service.VillageInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/village-info")
@RequiredArgsConstructor
public class AdminVillageInfoController {

    private final VillageInfoService villageInfoService;

    @GetMapping("/{section}")
    public Result<VillageInfo> getBySection(@PathVariable String section) {
        VillageInfo info = villageInfoService.getBySection(section);
        return Result.success(info);
    }

    @PutMapping("/{section}")
    public Result<?> saveOrUpdate(@PathVariable String section, @RequestBody VillageInfo info) {
        info.setSection(section);
        villageInfoService.saveOrUpdate(info);
        return Result.success();
    }
}
