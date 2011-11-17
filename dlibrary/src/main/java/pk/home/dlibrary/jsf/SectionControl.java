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
import pk.home.dlibrary.domain.Section;
import pk.home.dlibrary.domain.Section_;
import pk.home.dlibrary.service.SectionService;

/**
 * 
 * @author traveler
 */

@Scope("session")
@Component("sectionControl")
public class SectionControl extends AbstractBasicControl<Section> implements
		Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481201000229334506L;

	@Autowired
	private SectionService sectionService;

	@Override
	public void aInit() throws Exception {
		
	}
	
	@Override
	protected void aUpdate() throws Exception {
		
	}
	
	
	
	@Override
	protected Long aloadCount() throws Exception {
		return sectionService.count();
	}
	
	
	@Override
	protected List<Section> aload(LazyDataModel<Section> dataModel, int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters, SortOrderType sot) throws Exception {
		
		SingularAttribute<Section, ?> orderByAttribute = Section_.id;
		if(sortField != null && sortField.equals("keyName")){
			orderByAttribute = Section_.keyName;
		}
		
		return sectionService.getAllEntities(first, pageSize, orderByAttribute, sot);
	}
	

	@Override
	protected String aAdd() throws Exception {
		this.edited = new Section();
		return "/jsf/section/sectionOp.xhtml";
	}

	@Override
	protected String acAdd() throws Exception {
		sectionService.persist(edited);
		return this.retUrl;
	}

	@Override
	protected String aEdit() throws Exception {
		this.edited = sectionService.find(selected.getId());
		return "/jsf/section/sectionOp.xhtml";
	}

	@Override
	protected String acEdit() throws Exception {
		sectionService.merge(edited);
		return this.retUrl;
	}

	@Override
	protected String aDel() throws Exception {
		this.edited = sectionService.find(selected.getId());
		return "/jsf/section/sectionOp.xhtml";
	}

	@Override
	protected String acDel() throws Exception {
		sectionService.remove(edited);
		return this.retUrl;
	}

	

	

	

}
