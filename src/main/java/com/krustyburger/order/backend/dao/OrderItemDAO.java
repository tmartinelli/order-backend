package com.krustyburger.order.backend.dao;

import org.springframework.stereotype.Repository;

import com.krustyburger.order.backend.model.OrderItem;

@Repository
public class OrderItemDAO extends BaseDAO<OrderItem, Long> {

	public OrderItemDAO() {
		super(OrderItem.class);
	}

}
