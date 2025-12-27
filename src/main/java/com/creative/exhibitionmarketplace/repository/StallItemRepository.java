package com.creative.exhibitionmarketplace.repository;

import com.creative.exhibitionmarketplace.entity.StallItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StallItemRepository extends JpaRepository<StallItem, Long> {
    List<StallItem> findByStallId(Long stallId);

    List<StallItem> findByAvailableTrue();
}
