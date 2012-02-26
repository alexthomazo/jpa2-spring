package org.alexthomazo.blog.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.alexthomazo.blog.model.db.PhotoAlbum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test class for {@link AbstractDao}
 * 
 * @author Alexandre THOMAZO
 */
public class PhotoAlbumDaoTest extends AbstractDaoTest {

	@Autowired
	private IPhotoAlbumDao photoAlbumDao;
	
	@Test
	public void testCount() {
		assertEquals("Nb photos albums", 4, photoAlbumDao.count());
	}
	
	@Test
	public void testGet() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		PhotoAlbum album = photoAlbumDao.get(1);
		
		assertEquals("title", "Jack Johnson Show at Bercy", album.getTitle());
		assertEquals("description", "Jack Johnson & friends show at Bercy", album.getDescription());
		assertEquals("start_date", "2008-07-09", format.format(album.getStartDate()));
		assertEquals("end_date", "2008-07-10", format.format(album.getEndDate()));
		assertEquals("directory", "2008_07_09 - Jack Johnson", album.getDirectory());
		assertEquals("thumbfile", "1/img_3066.jpg", album.getThumbfile());
	}
	
	@Test
	public void testList() {
		List<PhotoAlbum> list = photoAlbumDao.list(1, 0, 0);
		assertEquals("nb albums alex", 4, list.size());
		
		list = photoAlbumDao.list(2, 0, 0);
		assertEquals("nb albums bob", 2, list.size());
		List<Integer> ids = extractIds(list);
		assertTrue("JJ", ids.contains(1));
		assertTrue("Paris", ids.contains(3));
		
		list = photoAlbumDao.list(3, 0, 0);
		ids = extractIds(list);
		assertEquals("nb albums chloe", 3, list.size());
		assertTrue("JJ", ids.contains(1));
		assertTrue("Holidays", ids.contains(2));
		assertTrue("Christmas", ids.contains(4));
		
		list = photoAlbumDao.list(1, 0, 2);
		ids = extractIds(list);
		assertEquals("nb albums start", 2, list.size());
		assertTrue("Christmas", ids.contains(4));
		assertTrue("Holidays", ids.contains(2));
		
		list = photoAlbumDao.list(1, 2, 2);
		ids = extractIds(list);
		assertEquals("nb albums end", 2, list.size());
		assertTrue("Paris", ids.contains(3));
		assertTrue("JJ", ids.contains(1));
	}

	private List<Integer> extractIds(List<PhotoAlbum> list) {
		List<Integer> ids = new ArrayList<Integer>();
		for (PhotoAlbum photoAlbum : list) {
			ids.add(photoAlbum.getPhotoAlbumId());
		}
		
		return ids;
	}
	
}
