package pk.home.dlibrary.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.domain.Item;


@Repository
@Transactional
public class ItemsDAO extends AbstractBasicDAO<Item>{

	@Override
	protected Class<Item> getTClass() {
		return Item.class;
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
	public Object getPrimaryKey(Item o) {
		return o.getId();
	}
	
}