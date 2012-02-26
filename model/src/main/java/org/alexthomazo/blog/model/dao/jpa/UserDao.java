package org.alexthomazo.blog.model.dao.jpa;

import org.alexthomazo.blog.model.dao.IUserDao;
import org.alexthomazo.blog.model.db.User;
import org.springframework.stereotype.Controller;

/**
 * JPA Implementation of the {@link IUserDao}
 * 
 * @author Alexandre THOMAZO
 */
@Controller
public class UserDao extends AbstractJPADAOImpl<User, Integer> implements IUserDao {

}
