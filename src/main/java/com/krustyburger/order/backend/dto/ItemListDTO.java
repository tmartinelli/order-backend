package com.krustyburger.order.backend.dto;

public class ItemListDTO {
	
	private Long[] items;

	private String address;
	
	public void setItems(Long[] items) {
		this.items = items;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public Long[] getItems() {
		return items;
	}
	
}
