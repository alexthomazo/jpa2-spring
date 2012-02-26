package org.alexthomazo.blog.model.dao;

import java.util.List;

import org.alexthomazo.blog.model.dao.jpa.AbstractJPADAOImpl;

/**
 * Interface defining the common operation on
 * objects in the database. This interface will
 * be implemented in {@link AbstractJPADAOImpl}
 * 
 * @author Alexandre THOMAZO
 *
 * @param <DomainObject> Type of the object to handle
 * @param <KeyType> Type of the key (eg. Integer.class)
 */
public interface AbstractDao <DomainObject, KeyType> {
	
	/**
	 * Get an object from the database by his id
	 * @param id Id of the object to retrieve
	 * @return Object if found or null
	 */
	public DomainObject get(KeyType id);

	/**
	 * Insert an object in the database
	 * @param object Object to insert
	 */
	public void insert(DomainObject object);

	/**
	 * Update an object in database.
	 * @param object Object to update
	 */
	public void save(DomainObject object);

	/**
	 * Delete an object from the database
	 * @param object Object to delete
	 */
	public void delete(DomainObject object);

	/**
	 * Get list of all objects
	 * @return List of all objects
	 */
	public List<DomainObject> getList();

	/**
	 * Delete all the objects in the database
	 */
	public void deleteAll();

	/**
	 * Count the objects in the database
	 * @return Number of objects
	 */
	public int count();
}
