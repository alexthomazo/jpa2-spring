package org.alexthomazo.blog.model.dao;

import java.util.List;

import org.alexthomazo.blog.model.db.PhotoAlbum;

/**
 * DAO Interface for handling {@link PhotoAlbum}
 * 
 * @author Alexandre THOMAZO
 *
 */
public interface IPhotoAlbumDao extends AbstractDao<PhotoAlbum, Integer> {

	/**
	 * List photo albums for a specific user.
	 * @param userId Id of the user
	 * @param start Start position of the first album to retrieve
	 * @param count Number of albums to retrieve
	 * @return List of albums meeting the conditions
	 */
	public List<PhotoAlbum> list(int userId, int start, int count);
	
}
