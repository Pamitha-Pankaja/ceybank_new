package com.example.ceybank.models;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "good_requisitions")
public class GoodRequisition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // or goodRequisitionId

    private String itemName;
    private String itemCode;
    private int requiredQuantity;
    private int approvedQuantity;
    private int issuedQuantity;  // this will trigger an 'OUT' transaction
    private LocalDate issuedDate;

    // Bi-directional link to transactions that reference this good requisition
    @OneToMany(mappedBy = "goodRequisition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;


}
