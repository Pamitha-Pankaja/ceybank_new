package com.example.ceybank.repositories;

import com.example.ceybank.models.GoodRequisitionItem;
import com.example.ceybank.models.StoreRequisitionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRequisitionItemRepository extends JpaRepository<GoodRequisitionItem, Long> {}

