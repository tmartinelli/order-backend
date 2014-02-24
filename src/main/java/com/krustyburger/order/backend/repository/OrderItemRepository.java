package com.krustyburger.order.backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.krustyburger.order.backend.dao.OrderItemDAO;
import com.krustyburger.order.backend.model.OrderItem;

@Repository
public class OrderItemRepository {

	private OrderItemDAO orderItemDAO;
	
	@Autowired
	public OrderItemRepository(OrderItemDAO orderItemDAO) {
		this.orderItemDAO = orderItemDAO;
	} 
	
	public OrderItem save(OrderItem orderItem) {
		if (orderItem.getId() == null) {
			this.orderItemDAO.persist(orderItem);
		} else {
			orderItem = this.orderItemDAO.merge(orderItem);
		}
		return orderItem;
	}
	
}
