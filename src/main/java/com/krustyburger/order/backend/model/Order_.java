package com.krustyburger.order.backend.model;

import java.util.Date;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Order.class)
public class Order_ {

	public static volatile SingularAttribute<Order, Long> id;
	public static volatile SingularAttribute<Order, OrderStatus> status;
	public static volatile SingularAttribute<Order, Date> statusDate;
	public static volatile SingularAttribute<Order, String> address;
	public static volatile ListAttribute<Order, OrderItem> items;
	
}
