package com.creative.exhibitionmarketplace.controller;

import com.creative.exhibitionmarketplace.entity.Stall;
import com.creative.exhibitionmarketplace.entity.StallItem;
import com.creative.exhibitionmarketplace.service.StallService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{stallId}/items")
    public List<StallItem> getStallItems(@PathVariable Integer stallId) {
        return stallService.getItemsByStall(stallId);
    }

    @PostMapping
    public Stall addStall(@RequestBody Stall stall) {
        return stallService.addStall(stall);
    }

    @PostMapping("/{stallId}/items")
    public StallItem addItemToStall(
            @PathVariable Integer stallId,
            @RequestBody StallItem item) {
        return stallService.addItemToStall(stallId, item);
    }

    //To search stall by category
    @GetMapping("/search")
    public ResponseEntity<List<Stall>> searchStalls(@RequestParam String keyword) {
        return ResponseEntity.ok(stallService.searchByKeyword(keyword));
    }

}
