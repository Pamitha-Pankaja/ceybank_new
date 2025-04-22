package com.example.ceybank.models;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;  // auto

    // Link back to the InventoryItem
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private InventoryItem inventoryItem;

    // Link to StoreRequisition if it is an incoming (received) transaction
    @ManyToOne
    @JoinColumn(name = "store_requisition_id")
    private StoreRequisition storeRequisition;

    // Link to GoodRequisition if it is an outgoing (issued) transaction
    @ManyToOne
    @JoinColumn(name = "good_requisition_id")
    private GoodRequisition goodRequisition;

    private LocalDate date;

    // These two fields can capture the in/out movement (one or both can be zero)
    private int receivedQuantity; // how many came in
    private int issuedQuantity;   // how many went out

    // You could also store a single `quantity` and a `type` (IN / OUT) if preferred


}
