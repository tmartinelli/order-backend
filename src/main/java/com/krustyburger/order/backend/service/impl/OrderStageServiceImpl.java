package com.krustyburger.order.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krustyburger.order.backend.model.OrderStage;
import com.krustyburger.order.backend.repository.OrderStageRepository;
import com.krustyburger.order.backend.service.OrderStageService;

@Service
public class OrderStageServiceImpl implements OrderStageService {

	@Autowired
	private OrderStageRepository orderStageRepository;
	
	@Override
	public OrderStage save(OrderStage orderStage) {
		return this.orderStageRepository.save(orderStage);
	}

}
