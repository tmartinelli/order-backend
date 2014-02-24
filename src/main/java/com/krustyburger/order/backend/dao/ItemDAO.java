package com.krustyburger.order.backend.dao;

import org.springframework.stereotype.Repository;

import com.krustyburger.order.backend.model.Item;

@Repository
public class ItemDAO extends BaseDAO<Item, Long> {

	public ItemDAO() {
		super(Item.class);
	}

}
