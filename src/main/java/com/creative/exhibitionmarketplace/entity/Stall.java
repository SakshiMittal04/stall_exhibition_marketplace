package com.creative.exhibitionmarketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "stalls")
public class Stall {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String location; // optional

    @OneToMany(mappedBy = "stall")
    private List<StallItem> items;
}
