package org.alexthomazo.blog.model.dao;

import java.util.List;

import org.alexthomazo.blog.model.db.AlbumItem;

/**
 * DAO Interface for handling {@link AlbumItem}
 * 
 * @author Alexandre THOMAZO
 *
 */
public interface IAlbumItemDao extends AbstractDao<AlbumItem, Integer> {

	/**
	 * Get items count for a Photo Album
	 * @param photoAlbumId Id of the photo album
	 * @return Total count of items in the photo Album
	 */
	public int count(int photoAlbumId);
	
	/**
	 * Get items for a Photo Album
	 * @param photoAlbumId Id of the photo album
	 * @param start Start position in result list
	 * @param count Max number items to retrieve 
	 * @return List of the AlbumItem
	 */
	public List<AlbumItem> getList(int photoAlbumId, int start, int count);
	
	/**
	 * Get all items for a Photo Album
	 * @param photoAlbumId Id of the photo album
	 * @return List of the AlbumItem
	 */
	public List<AlbumItem> getList(int photoAlbumId);
	
	/**
	 * Get following item(s) in the photo album
	 * @param item Current item
	 * @param count Items number to retrieve
	 * @return Following item(s)
	 */
	public List<AlbumItem> getNext(AlbumItem item, int count);
	
	/**
	 * Get previous item(s) in the photo album
	 * @param item Current item
	 * @param count Items number to retrieve
	 * @return Preview item(s)
	 */
	public List<AlbumItem> getPrev(AlbumItem item, int count);
	
	/**
	 * Get position of the item in the photo album starting from 0
	 * @param item Current item
	 * @return Position in the photo album (start at 0)
	 */
	public int getPos(AlbumItem item);
	
}
