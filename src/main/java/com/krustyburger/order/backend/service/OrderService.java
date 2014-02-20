package com.krustyburger.order.backend.service;

import java.util.List;

import com.krustyburger.order.backend.model.Order;
import com.krustyburger.order.backend.model.OrderStatus;

public interface OrderService {

	List<Order> findPendentAndInProgress();
	
	List<Order> findFinalized();
	
	Order findBy(Long id);
	
	Long add(Long[] items, String address);
	
	Order updateStatus(Long id, OrderStatus status);
	
}
