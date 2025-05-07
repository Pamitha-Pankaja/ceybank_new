package com.example.ceybank.repositories;

import com.example.ceybank.models.GoodRequisitionItem;
import com.example.ceybank.models.StoreRequisitionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodRequisitionItemRepository extends JpaRepository<GoodRequisitionItem, Long> {

    @Query("SELECT DISTINCT gri.issueNo FROM GoodRequisitionItem gri WHERE gri.goodRequisition.id = :requisitionId AND gri.issueNo IS NOT NULL")
    List<String> findDistinctIssueNosByGoodRequisitionId(@Param("requisitionId") Long requisitionId);

    List<GoodRequisitionItem> findByIssueNo(String issueNo);


}

