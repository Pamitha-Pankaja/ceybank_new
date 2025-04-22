package com.example.ceybank.models;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "store_requisitions")
public class StoreRequisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeRequisitionId; // e.g. SR001, SR002

    private LocalDate date;

    @OneToMany(mappedBy = "storeRequisition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StoreRequisitionItem> items;


}

