package com.krustyburger.order.backend.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.krustyburger.order.backend.model.Item;
import com.krustyburger.order.backend.model.Order;
import com.krustyburger.order.backend.model.OrderItem;
import com.krustyburger.order.backend.model.OrderStage;
import com.krustyburger.order.backend.model.OrderStatus;
import com.krustyburger.order.backend.repository.OrderRepository;
import com.krustyburger.order.backend.service.OrderItemService;
import com.krustyburger.order.backend.service.OrderService;
import com.krustyburger.order.backend.service.OrderStageService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderStageService orderStageService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Override
	public List<Order> findPendentAndInProgress() {
		return this.orderRepository.findBy(OrderStatus.PENDENT, OrderStatus.IN_PROGRESS);
	}
	
	@Override
	public List<Order> findFinalized() {
		return this.orderRepository.findBy(OrderStatus.FINALIZED);
	}	
	
	@Override
	public Order findBy(Long id) {
		try {
			return this.orderRepository.findBy(id);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	@Transactional
	public Long add(Long[] items, String address) {
		Order order = new Order();
		order.setStatusDate(new Date());
		order.setStatus(OrderStatus.PENDENT);
		order.setAddress(address);
		order = save(order);
		saveItems(order, items);
		return order.getId();
	}
	
	@Override
	public Order updateStatus(Long id, OrderStatus status) {
		Order order = findBy(id);		
		order.setStatus(status);
		order.setStatusDate(new Date());		
		order = this.save(order);
		return order;
	}
	
	private Order save(Order order) {
		OrderStatus currentStatus = null;
		try {
			currentStatus = findBy(order.getId()).getStatus();
		} catch (NoResultException e) {
			if (order.getId() != null) {
				throw e;
			}
		}
		order = this.orderRepository.save(order);
		if (currentStatus == null || !currentStatus.equals(order.getStatus())) {
			saveStage(order);
		}
		return order;
	}
	
	private void saveStage(Order order) {
		OrderStage orderStage = new OrderStage();
		orderStage.setOrder(order);
		orderStage.setStatus(order.getStatus());
		orderStage.setStatusDate(order.getStatusDate());
		this.orderStageService.save(orderStage);
	}
	
	private void saveItems(Order order, Long[] items) {
		for (int i = 0; i < items.length; i++) {
			Item item = new Item();
			item.setId(items[i]);
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setItem(item);
			this.orderItemService.save(orderItem);
		}
	}

}
