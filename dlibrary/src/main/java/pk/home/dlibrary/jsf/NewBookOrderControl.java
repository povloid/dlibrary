package pk.home.dlibrary.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pk.home.dlibrary.dao.AbstractBasicDAO.SortOrderType;
import pk.home.dlibrary.domain.Book;
import pk.home.dlibrary.domain.BookOrder;
import pk.home.dlibrary.domain.Book_;
import pk.home.dlibrary.domain.Disciple;
import pk.home.dlibrary.domain.Disciple_;
import pk.home.dlibrary.service.BookService;
import pk.home.dlibrary.service.DiscipleService;

@Scope("session")
@Component("newBookOrderControl")
public class NewBookOrderControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5811121979063733302L;

	@Autowired
	private DiscipleService discipleService;

	@Autowired
	private BookService bookService;

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

	public String addNewBookOrder() {

		this.newBookOrder = new BookOrder(); // После добавления создаем новый
												// пустой
		return null;
	}

	private Book selectedBook;

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

}
