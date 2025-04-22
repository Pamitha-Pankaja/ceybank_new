package com.example.ceybank.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory_items")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    //@Column(nullable = false, unique = true)
    @JsonProperty("itemCode")
    private String itemCode;

    @JsonProperty("itemName")
    private String itemName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("slug")
    private String slug;

    // Current available quantity
    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("category")
    private String category;

    // Reorder levels
    @JsonProperty("reOrderLevel")
    private int reOrderLevel;

    @JsonProperty("maximumReorderLevel")
    private int maximumReorderLevel;

    // If you are storing an image URL or path
    @JsonProperty("image")
    private String image;

    // Bi-directional link to all transactions for this item
    @OneToMany(mappedBy = "inventoryItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;


}

