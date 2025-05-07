package com.example.ceybank.repositories;

import com.example.ceybank.models.StoreRequisitionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRequisitionItemRepository extends JpaRepository<StoreRequisitionItem, Long> {
//    // Fetch distinct GRN numbers for a given store requisition ID
//    @Query("SELECT DISTINCT sri.grnNo FROM StoreRequisitionItem sri WHERE sri.storeRequisition.storeRequisitionId = :requisitionId AND sri.grnNo IS NOT NULL")
//    List<String> findDistinctGrnNosByStoreRequisitionId(@Param("requisitionId") String requisitionId);

    @Query("SELECT DISTINCT sri.grnNo FROM StoreRequisitionItem sri WHERE sri.storeRequisition.id = :requisitionId AND sri.grnNo IS NOT NULL")
    List<String> findDistinctGrnNosByStoreRequisitionId(@Param("requisitionId") Long requisitionId);


    // Fetch all items associated with a given GRN
    List<StoreRequisitionItem> findByGrnNo(String grnNo);
}

