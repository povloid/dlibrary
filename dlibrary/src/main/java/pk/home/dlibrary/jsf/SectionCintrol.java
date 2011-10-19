package pk.home.dlibrary.jsf;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pk.home.dlibrary.domain.Section;
import pk.home.dlibrary.service.SectionService;

/**
 * 
 * @author traveler
 */

@Scope("session")
@Component("sectionControl")
public class SectionCintrol extends AbstractBasicControl<Section> implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481201000229334506L;

	@Autowired
	private SectionService sectionService;

	@Override
	public void aInit() throws Exception {
		this.list = sectionService.getAllEntities();
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
