package pk.home.dlibrary.service;

import static org.junit.Assert.*;

import java.util.Date;
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

import pk.home.dlibrary.domain.Book;
import pk.home.dlibrary.domain.BookOrder;
import pk.home.dlibrary.domain.Disciple;
import pk.home.dlibrary.domain.Item;
import pk.home.dlibrary.domain.Section;


@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = { "file:./src/main/resources/applicationContext.xml" })
public class BookOrderItemsServiceTest {
	
	
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
	protected BookOrderItemsService bookOrderItemsService;
	
	@Autowired
	protected DiscipleService discipleService;
	
	@Autowired
	protected BookService bookService;
	
	@Autowired
	protected SectionService sectionService;
	
	
	/**
	 * Instantiates a new TestrbServiceTest.
	 * 
	 */
	public BookOrderItemsServiceTest() {
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
	@Rollback(false)
	public void testAddNewBookOrder() throws Exception {
		
		long unique = (new Date()).getTime();
		
		// Add Disciple
		Disciple disciple = new Disciple();
		disciple.setFname("Василий" + unique);
		disciple.setIname("Пупкин" + unique);
		disciple = discipleService.persist(disciple);
		
		
		// Add Section
		Section section = new Section();
		section.setKeyName("Cекция " + unique);
		section = sectionService.persist(section);
		
		
		// Add Book
		Book book = new Book();
		book.setTitle("Книга." + unique);
		book.setSection(section);
		
		book = bookService.persist(book);
		
		
		Book book2 = new Book();
		book2.setTitle("Книга 2." + unique);
		book2.setSection(section);
		
		book2 = bookService.persist(book2);
		
		// Add new BookOrder
		BookOrder bookOrder = new BookOrder();
		bookOrder.setCdate(new Date());
		bookOrder.setDescription("Пометки по ордеру......." + unique);
		bookOrder.setDisciple(disciple);
		
		
		Item item1 = new Item();
		item1.setBook(book);
		item1.setBookOrder(bookOrder);
		item1.setDescription("Описсание..." + unique);
		
		
		bookOrder.getItems().add(item1);
		
		Item item2 = new Item();
		item2.setBook(book2);
		item2.setBookOrder(bookOrder);
		item2.setDescription("Описсание... 2" + unique) ;
		
		
		bookOrder.getItems().add(item2);
		
		bookOrder = bookOrderItemsService.addNewBookOrder(bookOrder);
		
		assertTrue(bookOrder.getId() > 0);
		
	}
	
	@Test
	@Rollback(true)
	public void testSelectFindBookOrder() throws Exception {
		
		List<BookOrder> list = bookOrderItemsService.getAllEntities();
		
		Long id = list.get(0).getId();
		assertTrue(id != null);
		
		BookOrder bookOrder = bookOrderItemsService.find(id);
		
		System.out.println(">>>bookOrder = " + bookOrder.getId());
		
		assertTrue(bookOrder != null);
		assertTrue(bookOrder.getId() == id);
		assertTrue(bookOrder.getItems() != null);
		assertTrue(bookOrder.getItems().size() > 0);
		assertTrue(bookOrder.getItems().get(0) != null);
		
		
	}
	
	
	
	
}
