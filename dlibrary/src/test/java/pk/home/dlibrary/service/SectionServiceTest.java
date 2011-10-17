package pk.home.dlibrary.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import pk.home.dlibrary.domain.Section;
import pk.home.dlibrary.service.SectionService;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = { "file:./src/main/resources/applicationContext.xml" })
public class SectionServiceTest {

	
	/**
	 * The Spring application context.
	 * 
	 */
	@SuppressWarnings("unused")
	private ApplicationContext context;

	/**
	 * The service being tested, injected by Spring.
	 * 
	 */
	@Autowired
	protected SectionService sectionService;
	
	
	/**
	 * Instantiates a new TestrbServiceTest.
	 * 
	 */
	public SectionServiceTest() {
		setupRequestContext();
	}

	/**
	 * Sets Up the Request context
	 * 
	 */
	private void setupRequestContext() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		ServletRequestAttributes attributes = new ServletRequestAttributes(
				request);
		RequestContextHolder.setRequestAttributes(attributes);
	}
	
	
	
	@Test
	@Rollback(true)
	public void testSelect() throws Exception {

		List<Section> list = sectionService.getAllEntities();
		assertNotNull(list);
		System.out.println("count: " + list.size());

	}
	
	

	

	@Test
	@Rollback(true)
	public void add() throws Exception {

		int count1, count2;
		{
			List<Section> list = sectionService.getAllEntities();
			count1 = list.size();
			System.out.println("count 1: " + count1);
		}

		{
			Section section = new Section();
			section.setKeyName("Section 2");
			section.setDescription("Description ....");

			sectionService.persist(section);
		}

		{
			List<Section> list = sectionService.getAllEntities();
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

		section = sectionService.persist(section);

		
		section.setKeyName("111111");
		
		section = sectionService.refresh(section);

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

			sectionService.persist(section);
		}
		
		
		{
			String s2 = "11111";
			section.setKeyName(s2);
			section = sectionService.merge(section);
		
			
			assertEquals(s2, section.getKeyName());
		}
		
		
	}
	

	
	
	
}
