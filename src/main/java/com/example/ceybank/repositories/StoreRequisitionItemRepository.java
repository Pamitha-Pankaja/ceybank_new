package com.example.ceybank.repositories;

import com.example.ceybank.models.StoreRequisitionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRequisitionItemRepository extends JpaRepository<StoreRequisitionItem, Long> {}

