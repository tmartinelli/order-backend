package com.krustyburger.order.backend.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = -7505623403031862554L;

	private Long id;
	private String status;
	private Date statusDate;
	private String address;
	private List<OrderItemDTO> items;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}
	
	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<OrderItemDTO> getItems() {
		return items;
	}
	
	public void setItems(List<OrderItemDTO> items) {
		this.items = items;
	}
	
}
