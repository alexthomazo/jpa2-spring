package org.alexthomazo.blog.model.dao.jpa;

import org.alexthomazo.blog.model.dao.IGroupDao;
import org.alexthomazo.blog.model.db.Group;
import org.springframework.stereotype.Controller;

/**
 * JPA Implementation of the {@link IGroupDao}
 * 
 * @author Alexandre THOMAZO
 */
@Controller
public class GroupDao extends AbstractJPADAOImpl<Group, Integer> implements IGroupDao {

}
