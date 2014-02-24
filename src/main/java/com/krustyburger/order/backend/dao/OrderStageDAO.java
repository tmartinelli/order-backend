package com.krustyburger.order.backend.dao;

import org.springframework.stereotype.Repository;

import com.krustyburger.order.backend.model.OrderStage;

@Repository
public class OrderStageDAO extends BaseDAO<OrderStage, Long> {

	public OrderStageDAO() {
		super(OrderStage.class);
	}

}
