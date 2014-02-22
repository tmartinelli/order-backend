package com.krustyburger.order.backend.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderItem.class)
public class OrderItem_ {
	
	public static volatile SingularAttribute<OrderItem, Long> id;
	public static volatile SingularAttribute<OrderItem, Order> order;
	public static volatile SingularAttribute<OrderItem, Item> item;
	
	
}
