package com.krustyburger.order.backend.dao;

import org.springframework.stereotype.Repository;

import com.krustyburger.order.backend.model.Order;

@Repository
public class OrderDAO extends BaseDAO<Order, Long> {

	public OrderDAO() {
		super(Order.class);
	}

}
