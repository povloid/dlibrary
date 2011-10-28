package pk.home.dlibrary.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pk.home.dlibrary.dao.AbstractBasicDAO;
import pk.home.dlibrary.dao.GenreDAO;
import pk.home.dlibrary.domain.Genre;

@Service
@Transactional
public class GenreService extends AbstractBasicService<Genre> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2496704686542377340L;
	
	
	@Autowired
	private GenreDAO GenreDAO;

	@Override
	public AbstractBasicDAO<Genre> getAbstractBasicDAO() {
		return GenreDAO;
	}

}
