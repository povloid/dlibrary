package pk.home.dlibrary.jsf;

import java.io.Serializable;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pk.home.dlibrary.domain.Section;
import pk.home.dlibrary.jsf.libs.ABaseDialog;
import pk.home.dlibrary.service.SectionService;

@Scope("session")
@Component("sectionDialogControl")
public class SetionDialogControl extends ABaseDialog<Section> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6715230467519060032L;
	
	
	@Autowired
	private SectionService sectionService;
	


	@Override
	protected Section createNewEditedObject() throws Exception {
		return new Section();
	}
	
	
	@Override
	protected Section findEditedObject(String sKey) throws Exception {
		System.out.println(">>>sKey=" + sKey);
		return sectionService.find(Long.parseLong(sKey));
	}

	@Override
	protected void aInit() throws Exception {

	}

	@Override
	protected void prepareAddImpl() throws Exception {
	}

	@Override
	protected String confirmAddImpl() throws Exception {
		sectionService.persist(edited);
		return null;
	}

	@Override
	protected void prepareEditImpl() throws Exception {
	}

	@Override
	protected String confirmEditImpl() throws Exception {
		sectionService.merge(edited);
		return null;
	}

	@Override
	protected void prepareDelImpl() throws Exception {
	}

	@Override
	protected String confirmDelImpl() throws Exception {
		sectionService.remove(edited);
		return null;
	}


}
