package org.alexthomazo.blog.model.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.alexthomazo.blog.model.dao.AbstractDao;

/**
 * Implementation of common operations on objects with JPA 2.
 * 
 * @author Alexandre THOMAZO
 *
 * @param <T> Type of the objects to handle
 * @param <KeyType> Type of the primary key
 */
public abstract class AbstractJPADAOImpl<T, KeyType extends Serializable>
		implements AbstractDao<T, KeyType> {

	/** Entity Manager used to talk with the database */
	@PersistenceContext
	private EntityManager em;

	/** Class of the object */
	protected Class<T> domainClass = getDomainClass();

	/** Criteria Builder used to build criteria based query */
	private CriteriaBuilder builder;
	
	
	public T get(KeyType id) {
		return em.find(domainClass, id);
	}

	public void insert(T t) {
		em.persist(t);
	}

	public void save(T t) {
		em.merge(t);
	}

	public void delete(T t) {
		em.remove(t);
	}

	public List<T> getList() {
		CriteriaQuery<T> criteria = getBuilder().createQuery(domainClass);
		criteria.select(criteria.from(domainClass));
		return em.createQuery(criteria).getResultList();
	}

	public void deleteAll() {
		String hqlDelete = "delete " + domainClass.getName();
		em.createQuery(hqlDelete).executeUpdate();
	}

	public int count() {
		CriteriaBuilder b = getBuilder();
		CriteriaQuery<Long> criteria = b.createQuery(Long.class);
		criteria.select(b.count(criteria.from(domainClass)));

		return em.createQuery(criteria).getSingleResult().intValue();
	}

	/**
	 * Utility method for subclasses to retrieve the Criteria Builder
	 * @return Criteria Builder from entity manager
	 */
	protected CriteriaBuilder getBuilder() {
		if (builder == null) {
			builder = em.getCriteriaBuilder();
		}
		return builder;
	}

	/**
	 * Utility method for subclasses to retrieve the Entity Manager
	 * @return Entity Manager
	 */
	protected EntityManager getEm() {
		return em;
	}
	
	/**
	 * Method used to retrieve Class of Domain Object
	 * @return Domain Object Class
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getDomainClass() {
		//getting class of the first parameterized type
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
		
		return clazz;
	}

}
