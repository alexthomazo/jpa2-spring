package org.alexthomazo.blog.model.db;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Domain object representing a group
 * 
 * @author Alexandre THOMAZO
 */
@Entity
@Table(name="groups")
public class Group {
	
	private int groupId;
	private String title;
	
	private Set<User> users;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="group_id")
	public int getGroupId() {
		return groupId;
	}

	public String getTitle() {
		return title;
	}
	
	@ManyToMany(mappedBy="groups")
	public Set<User> getUsers() {
		return users;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
