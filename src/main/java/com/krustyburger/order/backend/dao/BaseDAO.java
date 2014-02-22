package com.krustyburger.order.backend.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDAO<T, ID> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BaseDAO.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private Class<T> clazz;
	
	public BaseDAO(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public List<T> find(CriteriaQuery<T> criteriaQuery) {
		TypedQuery<T> typedQuery = this.entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
    
	public T get(ID id) {
        return this.entityManager.find(this.clazz, id);
    }
	
    public T get(CriteriaQuery<T> criteriaQuery) {
		TypedQuery<T> typedQuery = this.entityManager.createQuery(criteriaQuery);
		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}    
	
	@Transactional
	public void persist(T object) {
		this.entityManager.persist(object);
	}

	@Transactional
	public T merge(T object) {
		return this.entityManager.merge(object);
	}

	@Transactional
	public Boolean remove(ID id) {
		Boolean removedSucess = true;
		try {
			T object = get(id);
			this.entityManager.remove(object);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			removedSucess = false;
		}
		return removedSucess;
	}
	
	@Transactional
	public void update(Map<String, Object> parameterList, String update) {
		Query query = entityManager.createNativeQuery(update);
		for (Map.Entry<String, Object> parameter : parameterList.entrySet()) {
			query.setParameter(parameter.getKey(), parameter.getValue());
		}
		query.executeUpdate();
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
}
