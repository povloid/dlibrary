package pk.home.dlibrary.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.dao.AbstractBasicDAO;
import pk.home.dlibrary.dao.DiscipleDAO;
import pk.home.dlibrary.domain.Disciple;

@Service
@Transactional
public class DiscipleService extends AbstractBasicService<Disciple> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2714082302900662520L;

	@Autowired
	private DiscipleDAO DiscipleDAO;

	@Override
	public AbstractBasicDAO<Disciple> getAbstractBasicDAO() {
		return DiscipleDAO;
	}

}
