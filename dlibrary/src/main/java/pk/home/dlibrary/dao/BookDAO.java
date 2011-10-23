package pk.home.dlibrary.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.domain.Book;


@Repository
@Transactional
public class BookDAO extends AbstractBasicDAO<Book>{

	@Override
	protected Class<Book> getTClass() {
		return Book.class;
	}

	/**
	 * EntityManager injected by Spring for persistence unit
	 * 
	 */
	@PersistenceContext(unitName = "")
	private EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Object getPrimaryKey(Book o) {
		return o.getId();
	}
	
}