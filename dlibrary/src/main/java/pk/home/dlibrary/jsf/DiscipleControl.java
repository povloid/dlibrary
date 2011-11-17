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
import pk.home.dlibrary.domain.Disciple;
import pk.home.dlibrary.domain.Disciple_;
import pk.home.dlibrary.service.DiscipleService;

/**
 * 
 * @author traveler
 */

@Scope("session")
@Component("discipleControl")
public class DiscipleControl extends AbstractBasicControl<Disciple> implements
		Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481201000229334508L;

	@Autowired
	private DiscipleService discipleService;

	@Override
	public void aInit() throws Exception {
		
	}
	
	@Override
	protected void aUpdate() throws Exception {
		
	}
	
	
	
	@Override
	protected Long aloadCount() throws Exception {
		return discipleService.count();
	}
	
	
	@Override
	protected List<Disciple> aload(LazyDataModel<Disciple> dataModel, int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters, SortOrderType sot) throws Exception {
		
		SingularAttribute<Disciple, ?> orderByAttribute = Disciple_.id;
		if(sortField != null && sortField.equals("fname")){
			orderByAttribute = Disciple_.fname;
		} else if(sortField != null && sortField.equals("iname")){
			orderByAttribute = Disciple_.iname;
		} 
		
		return discipleService.getAllEntities(first, pageSize, orderByAttribute, sot);
	}
	

	@Override
	protected String aAdd() throws Exception {
		this.edited = new Disciple();
		return "/jsf/disciple/discipleOp.xhtml";
	}

	@Override
	protected String acAdd() throws Exception {
		discipleService.persist(edited);
		return this.retUrl;
	}

	@Override
	protected String aEdit() throws Exception {
		this.edited = discipleService.find(selected.getId());
		return "/jsf/disciple/discipleOp.xhtml";
	}

	@Override
	protected String acEdit() throws Exception {
		discipleService.merge(edited);
		return this.retUrl;
	}

	@Override
	protected String aDel() throws Exception {
		this.edited = discipleService.find(selected.getId());
		return "/jsf/disciple/discipleOp.xhtml";
	}

	@Override
	protected String acDel() throws Exception {
		discipleService.remove(edited);
		return this.retUrl;
	}

	

	

	

}
