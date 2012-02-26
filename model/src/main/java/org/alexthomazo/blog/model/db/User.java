package org.alexthomazo.blog.model.db;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

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
 * Domain object representing a user.
 * 
 * @author Alexandre THOMAZO
 */
@Entity
@Table(name="users")
public class User {

	private int userId;
	private String login;
	private String pass;
	private String mail;
	private String nickname;
	private String firstname;
	private String name;
	private Date birthday;
	
	private Set<Group> groups;

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	public int getUserId() {
		return userId;
	}
	
	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}

	public String getMail() {
		return mail;
	}
	
	public String getNickname() {
		return nickname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getName() {
		return name;
	}

	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @return Get all the groups where the user's in
	 */
	@ManyToMany
	@JoinTable(name="user_group", 
		joinColumns=@JoinColumn(name="user_id"),
		inverseJoinColumns=@JoinColumn(name="group_id"))
	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * @return Nickname if defined or Firstname Lastname
	 */
	@Transient
	public String getDisplayName() {
		if (nickname != null) {
			return nickname;
		}
		return firstname + " " + name;
	}
	
	/**
	 * @return User's age
	 */
	@Transient
	public int getAge() {
		Calendar cBirthday = new GregorianCalendar();
		Calendar cToday = new GregorianCalendar();
		cBirthday.setTime(birthday);

		int yearDiff = cToday.get(Calendar.YEAR) - cBirthday.get(Calendar.YEAR);
		cBirthday.set(Calendar.YEAR, cToday.get(Calendar.YEAR));
		if (cBirthday.before(cToday)) {
			return yearDiff; // Birthday already celebrated this year
		} else {
			return Math.max(0, yearDiff - 1); // Need a max to avoid -1 for baby
		}
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return login;
	}
	
}
