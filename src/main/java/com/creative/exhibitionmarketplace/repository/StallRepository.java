package com.creative.exhibitionmarketplace.repository;

import com.creative.exhibitionmarketplace.entity.Stall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StallRepository extends JpaRepository<Stall, Integer> {
    List<Stall> findByDescriptionContainingIgnoreCase(String keyword);
}
