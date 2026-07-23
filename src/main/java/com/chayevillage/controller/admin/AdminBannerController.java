package com.chayevillage.controller.admin;

import com.chayevillage.common.PageResult;
import com.chayevillage.common.Result;
import com.chayevillage.entity.Banner;
import com.chayevillage.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/banners")
@RequiredArgsConstructor
public class AdminBannerController {

    private final BannerService bannerService;

    @GetMapping
    public Result<PageResult<Banner>> getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResult<Banner> result = bannerService.getPage(page, size);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<Banner> getById(@PathVariable Long id) {
        Banner banner = bannerService.getById(id);
        return Result.success(banner);
    }

    @PostMapping
    public Result<?> save(@RequestBody Banner banner) {
        bannerService.save(banner);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Banner banner) {
        banner.setId(id);
        bannerService.update(banner);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        bannerService.delete(id);
        return Result.success();
    }
}
