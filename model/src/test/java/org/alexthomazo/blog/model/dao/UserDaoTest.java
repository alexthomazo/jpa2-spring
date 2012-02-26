package org.alexthomazo.blog.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.alexthomazo.blog.model.dao.jpa.UserDao;
import org.alexthomazo.blog.model.db.Group;
import org.alexthomazo.blog.model.db.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test class for {@link UserDao}
 * 
 * @author Alexandre THOMAZO
 */
public class UserDaoTest extends AbstractDaoTest {

	@Autowired
	private IUserDao userDao;
	
	@Test
	public void testCount() {
		assertEquals("Users Nb", 3, userDao.count());
	}
	
	@Test
	public void testGet() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		User user = userDao.get(1);
		
		assertEquals("login", "alex", user.getLogin());
		assertEquals("pass", "098f6bcd4621d373cade4e832627b4f6", user.getPass());
		assertEquals("mail", "alex@foo.org", user.getMail());
		assertEquals("nickname", "Alex", user.getNickname());
		assertEquals("firstname", "Alexandre", user.getFirstname());
		assertEquals("name", "FOO", user.getName());
		assertEquals("birthday", "1984-12-22", format.format(user.getBirthday()));
		assertEquals("displayName", "Alex", user.getDisplayName());
		
		user = userDao.get(3);
		assertEquals("displayName", "Chloe FOO", user.getDisplayName());
	}
	
	public void testGroups() {
		User user = userDao.get(1);
		Set<Group> groups = user.getGroups();
		
		//extract groups id
		Set<Integer> groups_id = new HashSet<Integer>();
		for (Group g : groups) {
			groups_id.add(g.getGroupId());
		}
		
		assertEquals("Groups size", 3, groups.size());
		assertTrue("Group in", groups_id.contains(1));
		assertTrue("Group in", groups_id.contains(2));
		assertTrue("Group in", groups_id.contains(3));
	}
}
