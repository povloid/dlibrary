package pk.home.dlibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.dao.AbstractBasicDAO;
import pk.home.dlibrary.dao.SectionDAO;
import pk.home.dlibrary.domain.Section;



@Service
@Transactional
public class SectionService extends AbstractBasicService<Section>{
		
	@Autowired
	private SectionDAO sectionDAO;

	@Override
	public AbstractBasicDAO<Section> getAbstractBasicDAO() {
		return sectionDAO;
	}
	
}
