package pk.home.dlibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pk.home.dlibrary.dao.AbstractBasicDAO;
import pk.home.dlibrary.dao.BookOrderDAO;
import pk.home.dlibrary.dao.ItemsDAO;
import pk.home.dlibrary.domain.BookOrder;



@Service
@Transactional
public class BookOrderItemsService extends AbstractBasicService<BookOrder>{
	
	@Autowired
	private BookOrderDAO bookOrderDAO;
	
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
	public BookOrder addNewBookOrder(BookOrder bookOrder) throws Exception{
		if(bookOrder.getItems() == null || bookOrder.getItems().size() == 0)
			throw new Exception("<<<ERROR: The items is empty.");
		
		return bookOrderDAO.persist(bookOrder);
	}


	
	
	
	
	
	
	

	
	
	
	
	
	

}
