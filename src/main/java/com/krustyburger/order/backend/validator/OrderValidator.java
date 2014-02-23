package com.krustyburger.order.backend.validator;

import org.springframework.stereotype.Component;

import com.krustyburger.order.backend.exception.KburgerAPIException;
import com.krustyburger.order.backend.model.Order;

@Component
public class OrderValidator {

	public void validateAdd(Long[] items) throws KburgerAPIException {
		if (items == null || items.length == 0) {
			throw new KburgerAPIException("Item list can not be empty");
		}
	}
	
	public void validateUpdateStatus(Order order, Order currentOrder) throws KburgerAPIException {
		if (order.getId() != null && currentOrder == null) {
			throw new KburgerAPIException("Order not found for id " + order.getId());
		}
		if (currentOrder != null && currentOrder.getStatus().getNextStatus() == null){
			throw new KburgerAPIException("There is no next stage for the order");
		}
	}
	
}
