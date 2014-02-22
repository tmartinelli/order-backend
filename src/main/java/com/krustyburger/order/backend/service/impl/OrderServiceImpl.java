package com.krustyburger.order.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public List<Order> find() {
		return this.orderRepository.find();
	}
	
	@Override
	public Order findBy(Long id) {
		return this.orderRepository.findBy(id);
	}
	
	@Override
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
	public Order updateStatus(Long id) {
		Order order = findBy(id);		
		order.setStatus(order.getStatus().getNextStatus());
		order.setStatusDate(new Date());		
		order = this.save(order);
		return order;
	}
	
	private Order save(Order order) {
		Order currentOrder = order.getId() != null ? findBy(order.getId()) : null;
		validate(order, currentOrder);
		order = this.orderRepository.save(order);
		saveStage(order);
		return order;
	}
	
	private void validate(Order order, Order currentOrder) {
		if (order.getId() != null && currentOrder == null) {
			throw new RuntimeException("Order not found for id" + order.getId());
		}
		if (currentOrder != null && currentOrder.getStatus().getNextStatus() == null){
			throw new RuntimeException("There is no next step for the order");
		}
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
