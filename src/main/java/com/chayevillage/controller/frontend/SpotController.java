package com.chayevillage.controller.frontend;

import com.chayevillage.common.Result;
import com.chayevillage.entity.Spot;
import com.chayevillage.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/spots")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService spotService;

    @GetMapping
    public Result<List<Spot>> getList() {
        List<Spot> list = spotService.getActiveList();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result<Spot> getDetail(@PathVariable Long id) {
        Spot spot = spotService.getById(id);
        return Result.success(spot);
    }
}
