package org.alexthomazo.blog.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.alexthomazo.blog.model.dao.jpa.AlbumItemDao;
import org.alexthomazo.blog.model.db.AlbumItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test class for {@link AlbumItemDao}
 * 
 * @author Alexandre THOMAZO
 */
public class AlbumItemDaoTest extends AbstractDaoTest {

	@Autowired
	private IAlbumItemDao albumItemDao;
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	@Test
	public void testGet() {
		AlbumItem item = albumItemDao.get(1);
		
		assertEquals("file", "img_3066.jpg", item.getFile());
		assertEquals("type", AlbumItem.ItemType.PHOTO, item.getItemType());
		assertEquals("desc", "", item.getDescription());
		assertEquals("date", "2008-07-09 17:58:44", formatter.format(item.getShootdate()));
		assertEquals("latitude", new Float(48.8387), item.getLatitude());
		assertEquals("longitude", new Float(2.3785), item.getLongitude());
		assertEquals("selected", true, item.isSelected());
		assertEquals("album", "Jack Johnson Show at Bercy", item.getPhotoAlbum().getTitle());
		assertEquals("owner", "Alex", item.getCameraOwner().getNickname());
	}
	
	@Test
	public void testCount() {
		int count = albumItemDao.count(1);
		assertEquals(2, count);
	}
	
	@Test
	public void testGetList() {
		List<AlbumItem> items = albumItemDao.getList(1);
		
		assertEquals("nb items", 2, items.size());
		List<Integer> ids = extractIds(items);
		assertTrue(ids.contains(1));
		assertTrue(ids.contains(2));
	}
	
	@Test
	public void testGetNext() {
		AlbumItem item = albumItemDao.get(1);
		List<AlbumItem> next = albumItemDao.getNext(item, 1);
		
		assertEquals(1, next.size());
		assertEquals(new Integer(2), next.get(0).getAlbumItemId());
		
		item = next.get(0);
		next = albumItemDao.getNext(item, 1);
		assertEquals(0, next.size());
	}
	
	@Test
	public void testGetPrev() {
		AlbumItem item = albumItemDao.get(2);
		List<AlbumItem> prev = albumItemDao.getPrev(item, 1);
		
		assertEquals(1, prev.size());
		assertEquals(new Integer(1), prev.get(0).getAlbumItemId());
		
		item = prev.get(0);
		prev = albumItemDao.getPrev(item, 1);
		assertEquals(0, prev.size());
	}
	
	@Test
	public void testGetPos() {
		AlbumItem item = albumItemDao.get(2);
		int pos = albumItemDao.getPos(item);
		
		assertEquals(1, pos);
	}
	
	private List<Integer> extractIds(List<AlbumItem> list) {
		List<Integer> ids = new ArrayList<Integer>();
		for (AlbumItem item : list) {
			ids.add(item.getAlbumItemId());
		}
		
		return ids;
	}
}
