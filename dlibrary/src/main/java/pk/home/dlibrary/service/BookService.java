package pk.home.dlibrary.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.dao.AbstractBasicDAO;
import pk.home.dlibrary.dao.BookDAO;
import pk.home.dlibrary.domain.Book;

@Service
@Transactional
public class BookService extends AbstractBasicService<Book> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2541747798864090898L;

	@Autowired
	private BookDAO BookDAO;

	@Override
	public AbstractBasicDAO<Book> getAbstractBasicDAO() {
		return BookDAO;
	}

}
