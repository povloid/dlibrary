package pk.home.dlibrary.jsf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SingularAttribute;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pk.home.dlibrary.dao.AbstractBasicDAO.SortOrderType;
import pk.home.dlibrary.domain.Genre;
import pk.home.dlibrary.domain.Genre_;
import pk.home.dlibrary.service.GenreService;

/**
 * 
 * @author traveler
 */

@Scope("session")
@Component("genreControl")
public class GenreControl extends AbstractBasicControl<Genre> implements
		Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481201000229334507L;

	@Autowired
	private GenreService genreService;

	@Override
	public void aInit() throws Exception {
		
	}
	
	@Override
	protected void aUpdate() throws Exception {
		
	}
	
	
	
	@Override
	protected Long aloadCount() throws Exception {
		return genreService.count();
	}
	
	
	@Override
	protected List<Genre> aload(LazyDataModel<Genre> dataModel, int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters, SortOrderType sot) throws Exception {
		
		SingularAttribute<Genre, ?> orderByAttribute = Genre_.id;
		if(sortField != null && sortField.equals("keyName")){
			orderByAttribute = Genre_.keyName;
		}
		
		return genreService.getAllEntities(pageSize,first, orderByAttribute, sot);
	}
	

	@Override
	protected String aAdd() throws Exception {
		this.edited = new Genre();
		return "/jsf/genre/genreOp.xhtml";
	}

	@Override
	protected String acAdd() throws Exception {
		genreService.persist(edited);
		return this.retUrl;
	}

	@Override
	protected String aEdit() throws Exception {
		this.edited = genreService.find(selected.getId());
		return "/jsf/genre/genreOp.xhtml";
	}

	@Override
	protected String acEdit() throws Exception {
		genreService.merge(edited);
		return this.retUrl;
	}

	@Override
	protected String aDel() throws Exception {
		this.edited = genreService.find(selected.getId());
		return "/jsf/genre/genreOp.xhtml";
	}

	@Override
	protected String acDel() throws Exception {
		genreService.remove(edited);
		return this.retUrl;
	}

	

	

	

}
