package com.krustyburger.order.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.krustyburger.order.backend.dao.OrderStageDAO;
import com.krustyburger.order.backend.model.OrderStage;

@Repository
public class OrderStageRepository {

	private OrderStageDAO orderStageDAO;
	
	@Autowired
	public OrderStageRepository(OrderStageDAO orderStageDAO) {
		this.orderStageDAO = orderStageDAO;
	} 
	
	public OrderStage save(OrderStage orderStage) {
		if (orderStage.getId() == null) {
			this.orderStageDAO.persist(orderStage);
		} else {
			orderStage = this.orderStageDAO.merge(orderStage);
		}
		return orderStage;
	}
	
}
