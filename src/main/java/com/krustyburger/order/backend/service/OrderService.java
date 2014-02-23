package com.krustyburger.order.backend.service;

import java.util.List;

import com.krustyburger.order.backend.exception.KburgerAPIException;
import com.krustyburger.order.backend.model.Order;

public interface OrderService {

	List<Order> find();
	
	Order findBy(Long id);
	
	Order add(Long[] items, String address) throws KburgerAPIException ;
	
	Order updateStatus(Long id) throws KburgerAPIException ;
	
}
