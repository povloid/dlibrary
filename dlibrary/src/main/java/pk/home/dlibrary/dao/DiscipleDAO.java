package pk.home.dlibrary.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.domain.Disciple;

@Repository
@Transactional
public class DiscipleDAO extends AbstractBasicDAO<Disciple> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9141701327359867666L;

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