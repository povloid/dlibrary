package pk.home.dlibrary.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pk.home.dlibrary.dao.AbstractBasicDAO;
import pk.home.dlibrary.dao.BookDAO;
import pk.home.dlibrary.dao.BookOrderDAO;
import pk.home.dlibrary.dao.ItemsDAO;
import pk.home.dlibrary.domain.Book;
import pk.home.dlibrary.domain.BookOrder;
import pk.home.dlibrary.domain.Disciple;
import pk.home.dlibrary.domain.Item;



@Service
@Transactional
public class BookOrderItemsService extends AbstractBasicService<BookOrder>{
	
	@Autowired
	private BookOrderDAO bookOrderDAO;
	
	@Autowired
	private BookDAO bookDAO;
	
	@Override
	public AbstractBasicDAO<BookOrder> getAbstractBasicDAO() {
		return bookOrderDAO;
	}
	
	@Autowired
	private ItemsDAO ItemDAO;
		
	/**
	 * Create new order
	 * @param bookOrder
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=Exception.class)
	public BookOrder addNewBookOrder(BookOrder bookOrder) throws Exception{
		if(bookOrder.getItems() == null || bookOrder.getItems().size() == 0)
			throw new Exception("<<<ERROR: The items is empty.");
		
		if(bookOrder.getItems().size() == 0){
			throw new Exception("Ордер неможет быть сохранен. Список книг пуст.");
		}
		
		
		for(Item i: bookOrder.getItems()){
			Book tbook = bookDAO.find(i.getBook().getId());
			if(tbook == null){
				throw new Exception("Книга " + i.getBook().getTitle() +  " была удалена.");
			} else if(tbook.isBlocked()) {
				throw new Exception("Книга " + i.getBook().getTitle() +  " была заблокирована.");
			} else if(tbook.isReads()) {
				throw new Exception("Книга " + i.getBook().getTitle() +  " находится у читателя.");
			} else {
				
				i.getBook().setReads(true);
				bookDAO.merge(i.getBook()); // Иначе само не обновляет
				
			}
		}
		
		bookOrder.setCdate(new Date());
		
		return bookOrderDAO.persist(bookOrder);
	}
	
	/**
	 * Select actived orders
	 * @param disciple
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public List<BookOrder> findByDiscipleActiv(Disciple disciple) throws Exception{
		return bookOrderDAO.executeQueryByName("BookOrder.findByDiscipleActiv", new Object[]{disciple});
	}
	
	
	

}
