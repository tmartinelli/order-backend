package com.krustyburger.order.backend.model;

public enum OrderStatus {

	PENDENT,
	IN_PROGRESS,
	FINALIZED,
	DELIVERED;
	
	public OrderStatus getNextStatus(){
		switch (this) {
		case PENDENT:
			return IN_PROGRESS;
		case IN_PROGRESS:
			return FINALIZED;
		case FINALIZED:
			return DELIVERED;
		default:
			return null;
		}
	}
	
}
