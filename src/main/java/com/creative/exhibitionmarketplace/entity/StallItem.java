package com.creative.exhibitionmarketplace.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "stall_items")
public class StallItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "stall_id", nullable = false)
    @JsonBackReference
    private Stall stall;

    private String name;
    private String description;
    private BigDecimal price;
    private boolean available;
}
