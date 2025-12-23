package com.creative.exhibitionmarketplace.controller;

import com.creative.exhibitionmarketplace.entity.Stall;
import com.creative.exhibitionmarketplace.entity.StallItem;
import com.creative.exhibitionmarketplace.service.StallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/stalls")
public class StallController {
    private final StallService stallService;

    public StallController(StallService stallService) {
        this.stallService = stallService;
    }

    @GetMapping
    public List<Stall> getStalls() {
        return stallService.getAllStalls();
    }

    @PostMapping
    public Stall addStall(@RequestBody Stall stall) {
        return stallService.addStall(stall);
    }

    @PostMapping("/{stallId}/items")
    public StallItem addItem(@PathVariable String stallId,
                             @RequestBody StallItem item) {
        return stallService.addItemToStall(stallId, item);
    }
}
