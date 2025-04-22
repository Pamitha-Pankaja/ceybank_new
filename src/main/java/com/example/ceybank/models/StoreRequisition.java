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
    private Long id;  // or storeRequisitionId

    private String itemName;
    private String itemCode;
    private int requiredQuantity;
    private int approvedQuantity;
    private int receivedQuantity;  // this will trigger an 'IN' transaction
    private LocalDate receivedDate;

    private BigDecimal rate;
    private BigDecimal total;  // total cost, if applicable

    // Bi-directional link to transactions that reference this store requisition
    @OneToMany(mappedBy = "storeRequisition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;


}

