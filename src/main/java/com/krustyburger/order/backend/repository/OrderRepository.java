package com.krustyburger.order.backend.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.krustyburger.order.backend.dao.OrderDAO;
import com.krustyburger.order.backend.model.Order;
import com.krustyburger.order.backend.model.OrderItem;
import com.krustyburger.order.backend.model.OrderItem_;
import com.krustyburger.order.backend.model.OrderStatus;
import com.krustyburger.order.backend.model.Order_;

@Repository
public class OrderRepository {

	@Autowired
	private OrderDAO orderDAO;
	
	public Order save(Order order) {
		if (order.getId() == null) {
			this.orderDAO.persist(order);
		} else {
			order = this.orderDAO.merge(order);
		}
		return order;
	}
	
	public List<Order> find() {
		CriteriaBuilder criteriaBuilder = this.orderDAO.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
		Root<Order> root = criteriaQuery.from(Order.class);
		Fetch<Order, OrderItem> orderItemFetch =  root.fetch(Order_.items, JoinType.LEFT);
		orderItemFetch.fetch(OrderItem_.item);		
		Predicate[] predicates = {criteriaBuilder.notEqual(root.get(Order_.status), OrderStatus.DELIVERED)};
		criteriaQuery.where(predicates);
		criteriaQuery.select(root);
		return this.orderDAO.find(criteriaQuery);
	}	
	
	public Order findBy(Long id) {
		CriteriaBuilder criteriaBuilder = this.orderDAO.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
		Root<Order> root = criteriaQuery.from(Order.class);
		Fetch<Order, OrderItem> orderItemFetch =  root.fetch(Order_.items, JoinType.LEFT);
		orderItemFetch.fetch(OrderItem_.item);
		Predicate[] predicates = {criteriaBuilder.equal(root.get(Order_.id), id)};
		criteriaQuery.where(predicates);
		criteriaQuery.select(root);
		return this.orderDAO.get(criteriaQuery);
	}
	
}
