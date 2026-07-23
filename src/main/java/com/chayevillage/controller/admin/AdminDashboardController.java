package com.chayevillage.controller.admin;

import com.chayevillage.common.Result;
import com.chayevillage.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/dashboard")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final ProductService productService;
    private final ArticleService articleService;
    private final SpotService spotService;
    private final BannerService bannerService;

    @GetMapping("/overview")
    public Result<Map<String, Object>> overview() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("products", productService.count());
        data.put("articles", articleService.count());
        data.put("spots", spotService.count());
        data.put("banners", bannerService.count());
        return Result.success(data);
    }
}
