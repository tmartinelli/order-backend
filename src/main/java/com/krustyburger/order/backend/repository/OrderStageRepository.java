package com.krustyburger.order.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krustyburger.order.backend.model.OrderStage;

public interface OrderStageRepository extends JpaRepository<OrderStage, Long> {

}
