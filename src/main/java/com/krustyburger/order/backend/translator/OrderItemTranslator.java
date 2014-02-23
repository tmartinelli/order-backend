package com.krustyburger.order.backend.translator;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.krustyburger.order.backend.dto.OrderItemDTO;
import com.krustyburger.order.backend.model.OrderItem;

@Component
public class OrderItemTranslator {

	private ItemTranslator itemTranslator;
	
	@Autowired
	public OrderItemTranslator(ItemTranslator itemTranslator) {
		this.itemTranslator = itemTranslator;
	}
	
	public OrderItemDTO entityToDTO(OrderItem entity) {
		OrderItemDTO dto = null;
		if (entity != null) {
			dto = new OrderItemDTO();
			dto.setId(entity.getId());
			if (Hibernate.isInitialized(entity.getItem())) {
				dto.setItem(this.itemTranslator.entityToDTO(entity.getItem()));
			}
		}
		return dto;
	}
	
	public List<OrderItemDTO> entityToDTO(List<OrderItem> entities) {
		List<OrderItemDTO> dtos = null;
		if (entities != null && !entities.isEmpty()) {
			dtos = new ArrayList<>();
			for (OrderItem entity : entities) {
				dtos.add(entityToDTO(entity));
			}
		}
		return dtos;
	}
	
}
