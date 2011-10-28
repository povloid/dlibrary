package pk.home.dlibrary.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.domain.BookOrder;


@Repository
@Transactional
public class BookOrderDAO extends AbstractBasicDAO<BookOrder>{

	@Override
	protected Class<BookOrder> getTClass() {
		return BookOrder.class;
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
	public Object getPrimaryKey(BookOrder o) {
		return o.getId();
	}
	
}