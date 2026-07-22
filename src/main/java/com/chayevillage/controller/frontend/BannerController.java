package com.chayevillage.controller.frontend;

import com.chayevillage.common.Result;
import com.chayevillage.entity.Banner;
import com.chayevillage.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banners")
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;

    @GetMapping
    public Result<List<Banner>> getActiveList() {
        List<Banner> list = bannerService.getActiveList();
        return Result.success(list);
    }
}
