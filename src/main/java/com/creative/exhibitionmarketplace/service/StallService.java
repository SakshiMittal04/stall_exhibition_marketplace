package com.creative.exhibitionmarketplace.service;

import com.creative.exhibitionmarketplace.entity.Stall;
import com.creative.exhibitionmarketplace.entity.StallItem;
import com.creative.exhibitionmarketplace.repository.StallItemRepository;
import com.creative.exhibitionmarketplace.repository.StallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class StallService {
    private final StallRepository stallRepository;
    private final StallItemRepository stallItemRepository;

    public StallService(StallRepository stallRepository,
                        StallItemRepository stallItemRepository) {
        this.stallRepository = stallRepository;
        this.stallItemRepository = stallItemRepository;
    }

    public List<Stall> getAllStalls() {
        return stallRepository.findAll();
    }

    public Stall addStall(Stall stall) {
        return stallRepository.save(stall);
    }

    public StallItem addItemToStall(String stallId, StallItem item) {
        Stall stall = stallRepository.findById(stallId)
                .orElseThrow(() -> new RuntimeException("Stall not found"));

        item.setId(UUID.randomUUID().toString());
        item.setStall(stall);
        return stallItemRepository.save(item);
    }
}
