package com.creative.exhibitionmarketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table(name = "stall_items")
public class StallItem {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "stall_id")
    private Stall stall;

    private String name;
    private String description;
    private BigDecimal price;
    private boolean available;
}
