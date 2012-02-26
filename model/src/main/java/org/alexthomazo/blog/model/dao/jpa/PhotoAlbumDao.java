package org.alexthomazo.blog.model.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.alexthomazo.blog.model.dao.IPhotoAlbumDao;
import org.alexthomazo.blog.model.db.Group;
import org.alexthomazo.blog.model.db.Group_;
import org.alexthomazo.blog.model.db.PhotoAlbum;
import org.alexthomazo.blog.model.db.PhotoAlbum_;
import org.alexthomazo.blog.model.db.User;
import org.springframework.stereotype.Controller;

/**
 * JPA Implementation of the {@link IPhotoAlbumDao}
 * 
 * @author Alexandre THOMAZO
 */
@Controller
public class PhotoAlbumDao extends AbstractJPADAOImpl<PhotoAlbum, Integer>
		implements IPhotoAlbumDao {

	@Override
	public List<PhotoAlbum> list(int userId, int start, int count) {
		User user = new User();
		user.setUserId(userId);
		
		CriteriaBuilder b = getBuilder();
		
		//creating criteria
		CriteriaQuery<PhotoAlbum> q = b.createQuery(PhotoAlbum.class);
		Root<PhotoAlbum> albumRoot = q.from(PhotoAlbum.class);
		q.distinct(true);
		q.select(albumRoot);
		
		//joining group
		Join<PhotoAlbum, Group> groups = albumRoot.join(PhotoAlbum_.groups);
				
		//adding restriction
		q.where(
			b.or(
				b.isMember(user, albumRoot.get(PhotoAlbum_.usersAllowed)),
				b.and(
					b.isMember(user, groups.get(Group_.users)),
					b.isNotMember(user, albumRoot.get(PhotoAlbum_.usersDenied))
				)
			)
		);
		
		q.orderBy(b.desc(albumRoot.get(PhotoAlbum_.startDate)));
		
		
		TypedQuery<PhotoAlbum> tQuery = getEm().createQuery(q);

		if (start > 0) {
			tQuery.setFirstResult(start);
		}
		if (count > 0) {
			tQuery.setMaxResults(count);
		}
		
		return tQuery.getResultList();
	}

}
