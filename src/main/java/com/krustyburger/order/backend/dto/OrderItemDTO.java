package com.krustyburger.order.backend.dto;

import java.io.Serializable;

public class OrderItemDTO implements Serializable {
	
	private static final long serialVersionUID = 2929787416917997351L;

	private Long id;
	private ItemDTO item;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public ItemDTO getItem() {
		return item;
	}
	
	public void setItem(ItemDTO item) {
		this.item = item;
	}
	
}
