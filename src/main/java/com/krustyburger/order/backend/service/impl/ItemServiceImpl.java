package com.krustyburger.order.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krustyburger.order.backend.model.Item;
import com.krustyburger.order.backend.repository.ItemRepository;
import com.krustyburger.order.backend.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public List<Item> findAll() {
		return this.itemRepository.findAll();
	}

}
