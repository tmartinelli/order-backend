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
	public Order add(Long[] items, String address) throws KburgerAPIException {
		validateAdd(items);
		Order order = new Order();
		order.setStatusDate(new Date());
		order.setStatus(OrderStatus.PENDENT);
		order.setAddress(address);
		order = save(order);
		order.setItems(saveItems(order, items));
		return order;
	}
	
	private void validateAdd(Long[] items) throws KburgerAPIException {
		if (items == null || items.length == 0) {
			throw new KburgerAPIException("Item list can not be empty");
		}
	}
	
	@Override
	public Order updateStatus(Long id) throws KburgerAPIException {
		Order order = new Order();
		order.setId(id);
		Order currentOrder = findBy(id);
		validateUpdateStatus(order, currentOrder);
		currentOrder.setStatus(order.getStatus().getNextStatus());
		currentOrder.setStatusDate(new Date());		
		order = this.save(currentOrder);
		return order;
	}
	
	private Order save(Order order) throws KburgerAPIException {
		order = this.orderRepository.save(order);
		saveStage(order);
		return order;
	}
	
	private void validateUpdateStatus(Order order, Order currentOrder) throws KburgerAPIException {
		if (order.getId() != null && currentOrder == null) {
			throw new KburgerAPIException("Order not found for id " + order.getId());
		}
		if (currentOrder != null && currentOrder.getStatus().getNextStatus() == null){
			throw new KburgerAPIException("There is no next stage for the order");
		}
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
