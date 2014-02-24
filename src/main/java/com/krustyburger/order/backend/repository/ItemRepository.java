package com.krustyburger.order.backend.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.krustyburger.order.backend.dao.ItemDAO;
import com.krustyburger.order.backend.model.Item;

@Repository
public class ItemRepository {

	private ItemDAO itemDAO;
	
	@Autowired
	public ItemRepository(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	} 
	
	public Item save(Item item) {
		if (item.getId() == null) {
			this.itemDAO.persist(item);
		} else {
			item = this.itemDAO.merge(item);
		}
		return item;
	}
	
	public List<Item> findAll() {
		CriteriaBuilder criteriaBuilder = this.itemDAO.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
		Root<Item> root = criteriaQuery.from(Item.class);
		criteriaQuery.select(root);
		return this.itemDAO.find(criteriaQuery);
	}	
	
}
