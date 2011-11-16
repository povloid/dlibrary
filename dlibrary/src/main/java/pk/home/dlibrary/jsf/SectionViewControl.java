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
import pk.home.dlibrary.jsf.libs.ABaseCRUDView;
import pk.home.dlibrary.service.SectionService;

@Scope("session")
@Component("sectionViewControl")
public class SectionViewControl extends ABaseCRUDView<Section> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1167726642106354076L;

	@Autowired
	private SectionService sectionService;

	
	@Override
	protected void aInit() throws Exception {
		
	}

	@Override
	protected List<Section> aload(LazyDataModel<Section> dataModel, int first,
			int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters) throws Exception {
		
		
		
		SingularAttribute<Section, ?> orderByAttribute = Section_.id;
		if (sortField != null && sortField.equals("keyName")) {
			orderByAttribute = Section_.keyName;
		}

		return sectionService.getAllEntities(pageSize, first, orderByAttribute,
				SortOrderType.ASC);
	}
	
	
	@Override
	protected Long aloadCount() throws Exception {
		return sectionService.count();
	}

}
