package com.creative.exhibitionmarketplace.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stalls")
public class Stall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private String location;

    @OneToMany(mappedBy = "stall", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<StallItem> items;
}
