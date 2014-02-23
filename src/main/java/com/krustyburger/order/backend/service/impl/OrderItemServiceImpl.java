package com.krustyburger.order.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krustyburger.order.backend.model.OrderItem;
import com.krustyburger.order.backend.repository.OrderItemRepository;
import com.krustyburger.order.backend.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	private OrderItemRepository orderItemRepository;
	
	@Autowired
	public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
		this.orderItemRepository = orderItemRepository;
	}
	
	@Override
	public OrderItem save(OrderItem orderItem) {
		return this.orderItemRepository.save(orderItem);
	}

}
