package pk.home.dlibrary.jsf;

import java.io.Serializable;
import javax.persistence.metamodel.SingularAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pk.home.dlibrary.domain.Section;
import pk.home.dlibrary.domain.Section_;
import pk.home.dlibrary.jsf.libs.ABaseLazyLoadTableView;
import pk.home.dlibrary.service.SectionService;

@Scope("session")
@Component("sectionViewControl")
public class SectionViewControl extends ABaseLazyLoadTableView<Section> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1167726642106354076L;

	@Autowired
	private SectionService sectionService;

	
	@Override
	protected void aInit() throws Exception {
		
		SingularAttribute<Section, ?> orderByAttribute = Section_.id;
		if (csortField != null && csortField.equals("id")) {
			orderByAttribute = Section_.id;
		} else if (csortField != null && csortField.equals("keyName")) {
			orderByAttribute = Section_.keyName;
		}

		
		dataModel = sectionService.getAllEntities((page - 1) * rows, rows,  
						orderByAttribute, getSortOrderType());
	}


	@Override
	protected long initAllRowsCount() throws Exception {
			return sectionService.count();
	}

	

}
