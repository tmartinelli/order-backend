package com.krustyburger.order.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krustyburger.order.backend.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
