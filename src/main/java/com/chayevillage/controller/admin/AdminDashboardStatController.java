package com.chayevillage.controller.admin;

import com.chayevillage.common.Result;
import com.chayevillage.entity.DashboardStat;
import com.chayevillage.service.DashboardStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardStatController {

    private final DashboardStatService dashboardStatService;

    @GetMapping("/stats")
    public Result<List<DashboardStat>> getAll() {
        List<DashboardStat> list = dashboardStatService.getAll();
        return Result.success(list);
    }

    @PutMapping("/stats/{key}")
    public Result<?> saveOrUpdate(@PathVariable String key, @RequestBody DashboardStat stat) {
        stat.setStatKey(key);
        dashboardStatService.saveOrUpdate(stat);
        return Result.success();
    }
}
