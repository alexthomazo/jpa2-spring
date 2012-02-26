package org.alexthomazo.blog.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract Class used for handling tests.
 * It's starting the Spring container which starts
 * the H2 embed database.
 * Then, it loads some tests data.
 * 
 * @author Alexandre THOMAZO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/appDb.xml"})
@Transactional
public abstract class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	DataSource dataSrc;
	
	@PersistenceContext
	private EntityManager em;
	
	private static boolean databaseLoaded = false;
	
	@Before
	public void beforeTest() {
		setDataSource(dataSrc);
		
		if (!databaseLoaded) {
			super.executeSqlScript("classpath:test-data.sql", false);
			databaseLoaded = true;
		}
	}
	
	// Utility method
	protected void flush() {
		em.flush();
	}

	public DataSource getDataSrc() {
		return dataSrc;
	}

	public void setDataSrc(DataSource dataSrc) {
		this.dataSrc = dataSrc;
	}

}
