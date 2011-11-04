package pk.home.dlibrary.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pk.home.dlibrary.dao.AbstractBasicDAO.SortOrderType;
import pk.home.dlibrary.domain.Book;
import pk.home.dlibrary.domain.BookOrder;
import pk.home.dlibrary.domain.Book_;
import pk.home.dlibrary.domain.Disciple;
import pk.home.dlibrary.domain.Disciple_;
import pk.home.dlibrary.domain.Item;
import pk.home.dlibrary.service.BookOrderItemsService;
import pk.home.dlibrary.service.BookService;
import pk.home.dlibrary.service.DiscipleService;

@Scope("session")
@Component("newBookOrderControl")
@Transactional
public class NewBookOrderControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5811121979063733302L;

	@Autowired
	private DiscipleService discipleService;

	@Autowired
	private BookService bookService;

	@Autowired
	private BookOrderItemsService bookOrderItemsService;

	private String init;

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	public void init() {
		try {
			if (init != null && init.equals("true")) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initDisciples() {
		try {
			this.disciples.clear();
			this.disciples = discipleService.getAllEntities(Disciple_.fname,
					SortOrderType.ASC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initBooks() {
		try {
			this.books.clear();
			this.books = bookService.getAllEntities(Book_.title,
					SortOrderType.ASC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Disciple selectedDisciple;

	public Disciple getSelectedDisciple() {
		return selectedDisciple;
	}

	public void setSelectedDisciple(Disciple selectedDisciple) {
		this.selectedDisciple = selectedDisciple;
		initDiscipleCurrentBookOrders();
	}

	List<Disciple> disciples = new ArrayList<Disciple>();

	public List<Disciple> getDisciples() {
		return disciples;
	}

	public void setDisciples(List<Disciple> disciples) {
		this.disciples = disciples;
	}

	public List<Book> books = new ArrayList<Book>();

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	private int rows = 5;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	private BookOrder newBookOrder = new BookOrder();

	public BookOrder getNewBookOrder() {
		return newBookOrder;
	}

	public void setNewBookOrder(BookOrder newBookOrder) {
		this.newBookOrder = newBookOrder;
	}

	private Book selectedBook;

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	/**
	 * Добавление записи
	 */
	public void addItem() {
		Item item = new Item();
		item.setBookOrder(newBookOrder);
		item.setBook(selectedBook);

		boolean bookHasAdded = false;
		for (Item i : this.newBookOrder.getItems()) { // Проверка на уже
														// добавленные
			if (i.getBook().getId() == selectedBook.getId()) {
				bookHasAdded = true;
				break;
			}
		}

		if (!bookHasAdded && !selectedBook.isBlocked()
				&& !selectedBook.isReads())
			this.newBookOrder.getItems().add(item);
	}

	/**
	 * Save new order
	 * 
	 * @return
	 */
	public String addNewBookOrder() {
		try {

			this.newBookOrder.setDisciple(selectedDisciple);
			bookOrderItemsService.addNewBookOrder(this.newBookOrder);

			this.newBookOrder = new BookOrder(); // После добавления создаем
													// новый
			initDiscipleCurrentBookOrders();

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}
		return null;
	}

	List<BookOrder> discipleCurrentBookOrders = new ArrayList<BookOrder>();

	public List<BookOrder> DiscipleCurrentBookOrders() {

		try {
			return bookOrderItemsService.getAllEntities();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}

		return null;
	}

	public List<BookOrder> getDiscipleCurrentBookOrders() {
		return discipleCurrentBookOrders;
	}

	public void setDiscipleCurrentBookOrders(
			List<BookOrder> discipleCurrentBookOrders) {
		this.discipleCurrentBookOrders = discipleCurrentBookOrders;
	}

	@Transactional
	public void initDiscipleCurrentBookOrders() {

		if (this.selectedDisciple == null)
			return;

		try {
			this.discipleCurrentBookOrders = bookOrderItemsService
					.findByDiscipleActiv(this.selectedDisciple);

			for (BookOrder bo : this.discipleCurrentBookOrders) { // Lazy init
				if (bo.getItems() != null)
					bo.getItems().size();
			}

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}
	}

	private Item currentNewItem;

	public Item getCurrentNewItem() {
		return currentNewItem;
	}

	public void setCurrentNewItem(Item currentNewItem) {
		this.currentNewItem = currentNewItem;
	}

	public void deleteNewBook() {
		try {

			Item ti = null;
			
			for (Item i : newBookOrder.getItems()) 
				if (i.getBook().getId() == currentNewItem.getBook().getId()) {
					ti = i;
					break;
				}
			
			newBookOrder.getItems().remove(ti);
			
			
			for (Item i : newBookOrder.getItems()) 
				System.out.println("*>>>" + i.getBook().getTitle());
			
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}
	}

	// Возврат книг
	private Item curentRetItem;

	public Item getCurentRetItem() {
		return curentRetItem;
	}

	public void setCurentRetItem(Item curentRetItem) {
		System.out.println(">>>> " + curentRetItem);
		this.curentRetItem = curentRetItem;
	}

	private long curentRetItemId;

	public long getCurentRetItemId() {
		return curentRetItemId;
	}

	public void setCurentRetItemId(long curentRetItemId) {
		this.curentRetItemId = curentRetItemId;
	}

	private long currentBookOrderId;

	public long getCurrentBookOrderId() {
		return currentBookOrderId;
	}

	public void setCurrentBookOrderId(long currentBookOrderId) {
		this.currentBookOrderId = currentBookOrderId;
	}

	/**
	 * Return the book
	 */
	public void returnBook() {
		try {

			bookOrderItemsService.returnBook(curentRetItemId);

			initDiscipleCurrentBookOrders();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}
	}

	/**
	 * Return the book
	 */
	public void closeBookOrder() {
		try {

			bookOrderItemsService.closeBookOrder(currentBookOrderId);

			initDiscipleCurrentBookOrders();
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}
	}

}
