package com.chayevillage.controller.admin;

import com.chayevillage.common.PageResult;
import com.chayevillage.common.Result;
import com.chayevillage.entity.Spot;
import com.chayevillage.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/spots")
@RequiredArgsConstructor
public class AdminSpotController {

    private final SpotService spotService;

    @GetMapping
    public Result<PageResult<Spot>> getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResult<Spot> result = spotService.getPage(page, size);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<Spot> getById(@PathVariable Long id) {
        Spot spot = spotService.getById(id);
        return Result.success(spot);
    }

    @PostMapping
    public Result<?> save(@RequestBody Spot spot) {
        spotService.save(spot);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Spot spot) {
        spot.setId(id);
        spotService.update(spot);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        spotService.delete(id);
        return Result.success();
    }
}
