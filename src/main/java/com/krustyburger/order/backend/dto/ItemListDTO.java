package com.krustyburger.order.backend.dto;

import java.io.Serializable;

public class ItemListDTO implements Serializable {
	
	private static final long serialVersionUID = -6248384064802738043L;

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
