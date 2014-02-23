package com.krustyburger.order.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krustyburger.order.backend.exception.KburgerAPIException;
import com.krustyburger.order.backend.model.Item;
import com.krustyburger.order.backend.model.Order;
import com.krustyburger.order.backend.model.OrderItem;
import com.krustyburger.order.backend.model.OrderStage;
import com.krustyburger.order.backend.model.OrderStatus;
import com.krustyburger.order.backend.repository.OrderRepository;
import com.krustyburger.order.backend.service.OrderItemService;
import com.krustyburger.order.backend.service.OrderService;
import com.krustyburger.order.backend.service.OrderStageService;
import com.krustyburger.order.backend.validator.OrderValidator;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private OrderValidator orderValidator;
	private OrderStageService orderStageService;
	private OrderItemService orderItemService;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, OrderValidator orderValidator, OrderStageService orderStageService, OrderItemService orderItemService) {
		this.orderRepository = orderRepository;
		this.orderValidator = orderValidator;
		this.orderStageService = orderStageService;
		this.orderItemService = orderItemService;
	}
	
	@Override
	public List<Order> find() {
		return this.orderRepository.find();
	}
	
	@Override
	public Order findBy(Long id) {
		return this.orderRepository.findBy(id);
	}
	
	@Override
	public Order add(Long[] items, String address) throws KburgerAPIException {
		this.orderValidator.validateAdd(items);
		Order order = new Order();
		order.setStatusDate(new Date());
		order.setStatus(OrderStatus.PENDENT);
		order.setAddress(address);
		order = save(order);
		order.setItems(saveItems(order, items));
		return order;
	}
	
	@Override
	public Order updateStatus(Long id) throws KburgerAPIException {
		Order order = new Order();
		order.setId(id);
		Order currentOrder = findBy(id);
		this.orderValidator.validateUpdateStatus(order, currentOrder);
		currentOrder.setStatus(currentOrder.getStatus().getNextStatus());
		currentOrder.setStatusDate(new Date());		
		order = this.save(currentOrder);
		return order;
	}
	
	private Order save(Order order) throws KburgerAPIException {
		order = this.orderRepository.save(order);
		saveStage(order);
		return order;
	}
	
	private void saveStage(Order order) {
		OrderStage orderStage = new OrderStage();
		orderStage.setOrder(order);
		orderStage.setStatus(order.getStatus());
		orderStage.setStatusDate(order.getStatusDate());
		this.orderStageService.save(orderStage);
	}
	
	private List<OrderItem> saveItems(Order order, Long[] items) {
		List<OrderItem> itemList = new ArrayList<>();
		for (int i = 0; i < items.length; i++) {
			Item item = new Item();
			item.setId(items[i]);
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setItem(item);
			itemList.add(this.orderItemService.save(orderItem));
		}
		return itemList;
	}

}
