package pk.home.dlibrary.jsf;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pk.home.dlibrary.domain.Section;
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
		
		//SingularAttribute<Section, ?> orderByAttribute = Section_.id;
		//if (sortField != null && sortField.equals("keyName")) {
		//	orderByAttribute = Section_.keyName;
		//}

		//dataModel = sectionService.getAllEntities(pageSize, first, orderByAttribute,
		//		SortOrderType.ASC);
		
		dataModel = sectionService.getAllEntities(rows, (page - 1) * rows);
		
	}


	@Override
	protected long initAllRowsCount() throws Exception {
			return sectionService.count();
	}

	

}
