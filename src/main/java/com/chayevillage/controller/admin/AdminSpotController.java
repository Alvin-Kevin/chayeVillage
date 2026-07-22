package com.chayevillage.controller.admin;

import com.chayevillage.common.Result;
import com.chayevillage.entity.Spot;
import com.chayevillage.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/spots")
@RequiredArgsConstructor
public class AdminSpotController {

    private final SpotService spotService;

    @GetMapping
    public Result<List<Spot>> getActiveList() {
        List<Spot> list = spotService.getActiveList();
        return Result.success(list);
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
