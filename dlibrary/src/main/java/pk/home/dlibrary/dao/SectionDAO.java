package pk.home.dlibrary.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.domain.Section;

@Repository
@Transactional
public class SectionDAO extends AbstractBasicDAO<Section> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3272027126286728965L;

	@Override
	protected Class<Section> getTClass() {
		return Section.class;
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
	public Object getPrimaryKey(Section o) {
		return o.getId();
	}

}
