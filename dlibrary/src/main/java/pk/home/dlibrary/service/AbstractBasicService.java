package pk.home.dlibrary.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.dao.AbstractBasicDAO;

public abstract class AbstractBasicService<T extends Object> {

	public abstract AbstractBasicDAO<T> getAbstractBasicDAO();

	@Transactional
	public T persist(T o) throws Exception {
		getAbstractBasicDAO().persist(o);
		return o;
	}

	@Transactional
	public T refresh(T o) throws Exception {
		getAbstractBasicDAO().refresh(o);
		return o;
	}

	@Transactional
	public T merge(T o) throws Exception {
		return getAbstractBasicDAO().merge(o);
	}

	@Transactional
	public void remove(T object) throws Exception {
		getAbstractBasicDAO().remove(getAbstractBasicDAO().getManagedEntity(object));
	}

	@Transactional
	public T find(Object key) throws Exception {
		return getAbstractBasicDAO().find(key);
	}

	@Transactional
	public List<T> getAllEntities() throws Exception {
		return getAbstractBasicDAO().getAllEntities();
	}

	@Transactional
	public List<T> getAllEntities(int maxResults, int firstResult)
			throws Exception {
		return getAbstractBasicDAO().getAllEntities(maxResults, firstResult);
	}

	@Transactional
	public long count() throws Exception {
		return getAbstractBasicDAO().count();
	}
}
