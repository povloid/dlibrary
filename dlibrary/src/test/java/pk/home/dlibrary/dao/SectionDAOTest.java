package pk.home.dlibrary.dao;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import pk.home.dlibrary.dao.SectionDAO;
import pk.home.dlibrary.domain.Section;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "file:./src/main/resources/applicationContext.xml" })
public class SectionDAOTest {

	/**
	 * The DAO being tested, injected by Spring
	 * 
	 */
	private SectionDAO sectionDS;

	/**
	 * Method to allow Spring to inject the DAO that will be tested
	 * 
	 */
	@Autowired
	public void setDataStore(SectionDAO sectionDS) {
		this.sectionDS = sectionDS;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Rollback(true)
	public void testSelect() throws Exception {

		List<Section> list = sectionDS.getAllEntities();
		assertNotNull(list);
		System.out.println("count: " + list.size());

	}
	
	
	@Test
	@Rollback(true)
	public void testSelectByQueryName() throws Exception {

		List<Section> list = sectionDS.executeQueryByName("Section.findAll");
		assertNotNull(list);
		System.out.println("count: " + list.size());

	}
	
	
	@Test
	@Rollback(true)
	public void testExecuteQueryByNameSingleResult() throws Exception {
		Section section = sectionDS.executeQueryByNameSingleResult("Section.findAll");
		
		assertNotNull(section);
		
	}
	
	

	@Test
	@Rollback(true)
	public void add() throws Exception {

		int count1, count2;
		{
			List<Section> list = sectionDS.getAllEntities();
			count1 = list.size();
			System.out.println("count 1: " + count1);
		}

		{
			Section section = new Section();
			section.setKeyName("Section 2");
			section.setDescription("Description ....");

			sectionDS.persist(section);
		}

		{
			List<Section> list = sectionDS.getAllEntities();
			count2 = list.size();
			System.out.println("count 2: " + count2);
		}

		assertTrue(count1 < count2);
	}

	@Test
	@Rollback(true)
	public void refresh() throws Exception {

		String keyName = "Section 2";

		Section section = new Section();
		section.setKeyName(keyName);
		section.setDescription("Description ....");

		section = sectionDS.persist(section);

		
		section.setKeyName("111111");
		
		section = sectionDS.refresh(section);

		assertEquals(keyName, section.getKeyName());

	}
	
	
	@Test
	@Rollback(true)
	public void merge() throws Exception {

		Section section = null;
		
		{
			section = new Section();
			section.setKeyName("Section 2");
			section.setDescription("Description ....");

			sectionDS.persist(section);
		}
		
		
		{
			String s2 = "11111";
			section.setKeyName(s2);
			section = sectionDS.merge(section);
		
			
			assertEquals(s2, section.getKeyName());
		}
		
		
	}
	

}
