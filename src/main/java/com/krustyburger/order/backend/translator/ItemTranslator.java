package com.krustyburger.order.backend.translator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.krustyburger.order.backend.dto.ItemDTO;
import com.krustyburger.order.backend.model.Item;

@Component
public class ItemTranslator {

	public ItemDTO entityToDTO(Item entity) {
		ItemDTO dto = null;
		if (entity != null) {
			dto = new ItemDTO();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setDescription(entity.getDescription());
			dto.setPrice(entity.getPrice());
		}
		return dto;
	}
	
	public List<ItemDTO> entityToDTO(List<Item> entities) {
		List<ItemDTO> dtos = null;
		if (entities != null && !entities.isEmpty()) {
			dtos = new ArrayList<>();
			for (Item entity : entities) {
				dtos.add(entityToDTO(entity));
			}
		}
		return dtos;
	}
	
}
