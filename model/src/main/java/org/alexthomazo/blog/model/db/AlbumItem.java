package org.alexthomazo.blog.model.db;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Domain object representing an item (photo or video)
 * in an album.
 * 
 * @author Alexandre THOMAZO
 */
@Entity
@Table(name="album_items")
public class AlbumItem {

	/** Enum to describe the type of the item */
	public enum ItemType { PHOTO, VIDEO }
	
	private Integer albumItemId;
	private String file;
	private ItemType itemType;
	private String description;
	private Date shootdate;
	private Float latitude;
	private Float longitude;
	private boolean selected;
	
	private PhotoAlbum photoAlbum;
	private User cameraOwner;

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="album_item_id")
	public Integer getAlbumItemId() {
		return albumItemId;
	}

	public String getFile() {
		return file;
	}

	@Column(name="item_type")
	@Enumerated(EnumType.STRING)
	public ItemType getItemType() {
		return itemType;
	}

	public String getDescription() {
		return description;
	}

	public Date getShootdate() {
		return shootdate;
	}

	public Float getLatitude() {
		return latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public boolean isSelected() {
		return selected;
	}

	@ManyToOne
	@JoinColumn(name="photo_album_id")
	public PhotoAlbum getPhotoAlbum() {
		return photoAlbum;
	}

	@ManyToOne
	@JoinColumn(name="camera_owner")
	public User getCameraOwner() {
		return cameraOwner;
	}

	public void setAlbumItemId(Integer albumItemId) {
		this.albumItemId = albumItemId;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setShootdate(Date shootdate) {
		this.shootdate = shootdate;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setPhotoAlbum(PhotoAlbum photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

	public void setCameraOwner(User cameraOwner) {
		this.cameraOwner = cameraOwner;
	}
	
}
