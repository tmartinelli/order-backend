package com.krustyburger.order.backend.translator;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.krustyburger.order.backend.dto.OrderDTO;
import com.krustyburger.order.backend.model.Order;

@Component
public class OrderTranslator {

	@Autowired
	private OrderItemTranslator orderItemTranslator;
	
	public OrderDTO entityToDTO(Order entity) {
		OrderDTO dto = null;
		if (entity != null) {
			dto = new OrderDTO();
			dto.setId(entity.getId());
			dto.setStatus(entity.getStatus().name());
			dto.setStatusDate(entity.getStatusDate());
			dto.setAddress(entity.getAddress());
			if (Hibernate.isInitialized(entity.getItems())) {
				dto.setItems(this.orderItemTranslator.entityToDTO(entity.getItems()));
			}
		}
		return dto;
	}
	
	public List<OrderDTO> entityToDTO(List<Order> entities) {
		List<OrderDTO> dtos = null;
		if (entities != null && !entities.isEmpty()) {
			dtos = new ArrayList<>();
			for (Order entity : entities) {
				dtos.add(entityToDTO(entity));
			}
		}
		return dtos;
	}
	
}
