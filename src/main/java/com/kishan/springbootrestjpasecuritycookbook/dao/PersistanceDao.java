package com.kishan.springbootrestjpasecuritycookbook.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class PersistanceDao<T extends Object, PK extends Serializable> {

	private final Class<T> type;

	@Autowired
	private EntityManager entityManager;
	
	public PersistanceDao(Class<T> type) {
		super();
		this.type = type;
	}
	
	public EntityManager getManager() {
		return entityManager;
	}
	
	public Class<T> getType() {
		return type;
	}
	
	public T findOne(final PK id) {
        return entityManager.find(type, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + type.getName()).getResultList();
    }

    public T create(final T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(final T entity) {
        return entityManager.merge(entity);
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(final PK entityId) {
        final T entity = findOne(entityId);
        delete(entity);
    }
}
