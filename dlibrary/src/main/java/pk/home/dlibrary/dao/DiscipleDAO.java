package pk.home.dlibrary.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.domain.Disciple;


@Repository
@Transactional
public class DiscipleDAO extends AbstractBasicDAO<Disciple>{

	@Override
	protected Class<Disciple> getTClass() {
		return Disciple.class;
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
	public Object getPrimaryKey(Disciple o) {
		return o.getId();
	}
	
}