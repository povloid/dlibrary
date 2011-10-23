package pk.home.dlibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.dao.AbstractBasicDAO;
import pk.home.dlibrary.dao.DiscipleDAO;
import pk.home.dlibrary.domain.Disciple;



@Service
@Transactional
public class DiscipleService extends AbstractBasicService<Disciple>{
		
	@Autowired
	private DiscipleDAO DiscipleDAO;

	@Override
	public AbstractBasicDAO<Disciple> getAbstractBasicDAO() {
		return DiscipleDAO;
	}
	
}
