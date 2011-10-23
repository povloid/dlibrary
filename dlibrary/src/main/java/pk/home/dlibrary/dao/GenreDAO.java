package pk.home.dlibrary.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.domain.Genre;


@Repository
@Transactional
public class GenreDAO extends AbstractBasicDAO<Genre>{

	@Override
	protected Class<Genre> getTClass() {
		return Genre.class;
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
	public Object getPrimaryKey(Genre o) {
		return o.getId();
	}
	
}