package com.krustyburger.order.backend.service;

import java.util.List;

import com.krustyburger.order.backend.model.Item;

public interface ItemService {

	List<Item> findAll();
	
}
