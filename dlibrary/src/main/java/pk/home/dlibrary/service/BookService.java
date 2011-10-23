package pk.home.dlibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.dao.AbstractBasicDAO;
import pk.home.dlibrary.dao.BookDAO;
import pk.home.dlibrary.domain.Book;



@Service
@Transactional
public class BookService extends AbstractBasicService<Book>{
		
	@Autowired
	private BookDAO BookDAO;

	@Override
	public AbstractBasicDAO<Book> getAbstractBasicDAO() {
		return BookDAO;
	}
	
}
