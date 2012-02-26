package org.alexthomazo.blog.model.db;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Domain object representing a photo album.
 * 
 * @author Alexandre THOMAZO
 */
@Entity
@Table(name="photo_albums")
public class PhotoAlbum {

	private int photoAlbumId;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private String directory;
	private String thumbfile;
	
	private Collection<Group> groups;
	private Collection<User> usersAllowed;
	private Collection<User> usersDenied;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="photo_album_id")
	public int getPhotoAlbumId() {
		return photoAlbumId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}

	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}

	public String getDirectory() {
		return directory;
	}

	public String getThumbfile() {
		return thumbfile;
	}

	/**
	 * @return All groups allowed to see this photo album
	 */
	@ManyToMany
	@JoinTable(name="photo_album_group", 
		joinColumns=@JoinColumn(name="photo_album_id"),
		inverseJoinColumns=@JoinColumn(name="group_id"))
	public Collection<Group> getGroups() {
		return groups;
	}
	
	/**
	 * @return All users allowed access the album despite the groups
	 */
	@ManyToMany
	@JoinTable(name="photo_album_user_allowed", 
		joinColumns=@JoinColumn(name="photo_album_id"),
		inverseJoinColumns=@JoinColumn(name="user_id"))
	public Collection<User> getUsersAllowed() {
		return usersAllowed;
	}

	/**
	 * @return All users denied access the album despite the groups
	 */
	@ManyToMany
	@JoinTable(name="photo_album_user_denied", 
		joinColumns=@JoinColumn(name="photo_album_id"),
		inverseJoinColumns=@JoinColumn(name="user_id"))
	public Collection<User> getUsersDenied() {
		return usersDenied;
	}

	/**
	 * Get starting year of the album
	 * @return Starting year of the album
	 */
	@Transient
	public int getYear() {
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(startDate);
		return c.get(Calendar.YEAR);
	}
	

	public void setPhotoAlbumId(int photoAlbumId) {
		this.photoAlbumId = photoAlbumId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public void setThumbfile(String thumbfile) {
		this.thumbfile = thumbfile;
	}

	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}

	public void setUsersAllowed(Collection<User> usersAllowed) {
		this.usersAllowed = usersAllowed;
	}

	public void setUsersDenied(Collection<User> usersDenied) {
		this.usersDenied = usersDenied;
	}

}
