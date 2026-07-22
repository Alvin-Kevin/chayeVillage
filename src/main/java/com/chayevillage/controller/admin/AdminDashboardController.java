package com.chayevillage.controller.admin;

import com.chayevillage.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    @GetMapping("/overview")
    public Result<Map<String, Object>> overview() {
        // Return counts for admin dashboard overview.
        // The service calls to get counts would normally be injected here.
        // For now, return placeholder structure.
        Map<String, Object> data = new HashMap<>();
        data.put("productCount", "0");
        data.put("articleCount", "0");
        data.put("spotCount", "0");
        data.put("bannerCount", "0");
        return Result.success(data);
    }
}
