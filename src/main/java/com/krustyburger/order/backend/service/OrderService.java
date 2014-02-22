package com.krustyburger.order.backend.service;

import java.util.List;

import com.krustyburger.order.backend.model.Order;

public interface OrderService {

	List<Order> find();
	
	Order findBy(Long id);
	
	Long add(Long[] items, String address);
	
	Order updateStatus(Long id);
	
}
