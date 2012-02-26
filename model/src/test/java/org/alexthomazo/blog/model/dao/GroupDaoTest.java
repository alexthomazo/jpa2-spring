package org.alexthomazo.blog.model.dao;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.alexthomazo.blog.model.dao.jpa.GroupDao;
import org.alexthomazo.blog.model.db.Group;
import org.alexthomazo.blog.model.db.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test class for {@link GroupDao}
 * 
 * @author Alexandre THOMAZO
 */
public class GroupDaoTest extends AbstractDaoTest {

	@Autowired
	private IGroupDao groupDao;
	
	@Test
	public void testCount() {
		assertEquals("Group Nb", 3, groupDao.count());
	}
	
	@Test
	public void testGet() {
		Group group = groupDao.get(2);
		
		assertEquals("name", "Friends", group.getTitle());
	}
	
	@Test
	public void testUsers() {
		Group group = groupDao.get(1);
		Set<User> users = group.getUsers();
		
		assertEquals("nbUsers", 2, users.size());
	}
}
