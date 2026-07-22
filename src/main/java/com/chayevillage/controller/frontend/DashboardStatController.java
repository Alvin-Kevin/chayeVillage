package com.chayevillage.controller.frontend;

import com.chayevillage.common.Result;
import com.chayevillage.entity.DashboardStat;
import com.chayevillage.service.DashboardStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardStatController {

    private final DashboardStatService dashboardStatService;

    @GetMapping("/stats")
    public Result<List<DashboardStat>> getAll() {
        List<DashboardStat> list = dashboardStatService.getAll();
        return Result.success(list);
    }
}
