package org.alexthomazo.blog.model.dao.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.alexthomazo.blog.model.dao.IAlbumItemDao;
import org.alexthomazo.blog.model.db.AlbumItem;
import org.alexthomazo.blog.model.db.AlbumItem_;
import org.springframework.stereotype.Controller;

/**
 * JPA Implementation of the {@link IAlbumItemDao}
 * 
 * @author Alexandre THOMAZO
 */
@Controller
public class AlbumItemDao extends AbstractJPADAOImpl<AlbumItem, Integer>
		implements IAlbumItemDao {

	@Override
	public int count(int photoAlbumId) {
		CriteriaBuilder b = getBuilder();
		
		//creating criteria
		CriteriaQuery<Long> q = b.createQuery(Long.class);
		Root<AlbumItem> item = q.from(AlbumItem.class);
		q.select(b.count(item));
		
		//adding restriction
		q.where(b.equal(item.get(AlbumItem_.photoAlbum), photoAlbumId));
		
		return getEm().createQuery(q).getSingleResult().intValue();
	}

	@Override
	public List<AlbumItem> getList(int photoAlbumId, int start, int count) {
		CriteriaBuilder b = getBuilder();
		
		//creating criteria
		CriteriaQuery<AlbumItem> q = b.createQuery(AlbumItem.class);
		Root<AlbumItem> item = q.from(AlbumItem.class);
		q.select(item);
		
		//joins to fetch
		item.fetch(AlbumItem_.cameraOwner);
		item.fetch(AlbumItem_.photoAlbum);
		
		//adding restriction
		q.where(b.equal(item.get(AlbumItem_.photoAlbum), photoAlbumId));
		
		//ordering
		q.orderBy(
			b.asc(item.get(AlbumItem_.shootdate)),
			b.asc(item.get(AlbumItem_.file))
		);
		
		//limit result
		TypedQuery<AlbumItem> tQuery = getEm().createQuery(q);
		if (start > 0) {
			tQuery.setFirstResult(start);
		}
		if (count > 0) {
			tQuery.setMaxResults(count);
		}
		
		return tQuery.getResultList();
	}

	@Override
	public List<AlbumItem> getList(int photoAlbumId) {
		return getList(photoAlbumId, 0, 0);
	}

	@Override
	public List<AlbumItem> getNext(AlbumItem item, int count) {
		CriteriaBuilder b = getBuilder();
		
		//creating criteria
		CriteriaQuery<AlbumItem> q = b.createQuery(AlbumItem.class);
		Root<AlbumItem> itemRoot = q.from(AlbumItem.class);
		q.select(itemRoot);
		
		//joins to fetch
		itemRoot.fetch(AlbumItem_.cameraOwner);
		itemRoot.fetch(AlbumItem_.photoAlbum);
		
		//adding restriction
		q.where(
			b.equal(itemRoot.get(AlbumItem_.photoAlbum), item.getPhotoAlbum().getPhotoAlbumId()),
			b.or(
				b.greaterThan(itemRoot.get(AlbumItem_.shootdate), item.getShootdate()),
				b.and(
					b.equal(itemRoot.get(AlbumItem_.shootdate), item.getShootdate()),
					b.greaterThan(itemRoot.get(AlbumItem_.file), item.getFile())
				)
			)
		);
		
		//ordering
		q.orderBy(
			b.asc(itemRoot.get(AlbumItem_.shootdate)),
			b.asc(itemRoot.get(AlbumItem_.file))
		);
		
		//limit result
		TypedQuery<AlbumItem> tQuery = getEm().createQuery(q);
		if (count > 0) {
			tQuery.setMaxResults(count);
		}
		
		return tQuery.getResultList();
	}

	@Override
	public List<AlbumItem> getPrev(AlbumItem item, int count) {
		CriteriaBuilder b = getBuilder();
		
		//creating criteria
		CriteriaQuery<AlbumItem> q = b.createQuery(AlbumItem.class);
		Root<AlbumItem> itemRoot = q.from(AlbumItem.class);
		q.select(itemRoot);
		
		//joins to fetch
		itemRoot.fetch(AlbumItem_.cameraOwner);
		itemRoot.fetch(AlbumItem_.photoAlbum);
		
		//adding restriction
		q.where(
			b.equal(itemRoot.get(AlbumItem_.photoAlbum), item.getPhotoAlbum().getPhotoAlbumId()),
			b.or(
				b.lessThan(itemRoot.get(AlbumItem_.shootdate), item.getShootdate()),
				b.and(
					b.equal(itemRoot.get(AlbumItem_.shootdate), item.getShootdate()),
					b.lessThan(itemRoot.get(AlbumItem_.file), item.getFile())
				)
			)
		);
		
		//ordering
		q.orderBy(
			b.desc(itemRoot.get(AlbumItem_.shootdate)),
			b.desc(itemRoot.get(AlbumItem_.file))
		);
		
		//limit result
		TypedQuery<AlbumItem> tQuery = getEm().createQuery(q);
		if (count > 0) {
			tQuery.setMaxResults(count);
		}
		
		return tQuery.getResultList();
	}

	@Override
	public int getPos(AlbumItem item) {
		CriteriaBuilder b = getBuilder();
		
		//creating criteria
		CriteriaQuery<Long> q = b.createQuery(Long.class);
		Root<AlbumItem> itemRoot = q.from(AlbumItem.class);
		q.select(b.count(itemRoot));
		
		//adding restriction
		q.where(
			b.equal(itemRoot.get(AlbumItem_.photoAlbum), item.getPhotoAlbum().getPhotoAlbumId()),
			b.or(
				b.lessThan(itemRoot.get(AlbumItem_.shootdate), item.getShootdate()),
				b.and(
					b.equal(itemRoot.get(AlbumItem_.shootdate), item.getShootdate()),
					b.lessThan(itemRoot.get(AlbumItem_.file), item.getFile())
				)
			)
		);
		
		return getEm().createQuery(q).getSingleResult().intValue();
	}

}
