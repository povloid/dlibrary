package pk.home.dlibrary.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.lucene.analysis.ReusableAnalyzerBase;
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
public class SectionCintrol implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481201000229334506L;

	/**
	 * Режим редактирования
	 * 
	 * @author dev_sport
	 * 
	 */
	private enum Mode {
		VIEW, ADD, EDIT, DEL
	}

	private Mode mode = Mode.ADD;

	public void setMode(String mode) {
		this.mode = Mode.valueOf(mode);
		System.out.println(">>>MODE: " + this.mode);
	}

	public String getMode() {
		return this.mode.toString();
	}

	public boolean isAdd() {
		return mode == Mode.ADD;
	}

	public boolean isEdit() {
		return mode == Mode.EDIT;
	}

	public boolean isDel() {
		return mode == Mode.DEL;
	}

	private Object selected;

	public Object getSelected() {
		return selected;
	}

	public void setSelected(Object selected) {
		this.selected = selected;
		System.out.println(">>>selected: " + this.selected);
	}

	private Section edited;

	public Section getEdited() {
		return edited;
	}

	public void setEdited(Section edited) {
		this.edited = edited;
	}

	@Autowired
	private SectionService sectionService;

	
	
	


	List<Section> list;

	public void init() {
		try {
			this.list = sectionService.getAllEntities();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Section> getList() {
		return this.list;
	}

	
	private String retUrl;
	
	public String getRetUrl() {
		return retUrl;
	}

	public void setRetUrl(String retUrl) {
		this.retUrl = retUrl + "?faces-redirect=true";
	}
	
	
	public String cancel(){
		return this.retUrl;
	}
	
	public String add() {
		try {
			this.edited = new Section();
			this.mode = Mode.ADD;
			return "/jsf/section/sectionOp.xhtml";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String cAdd(){
		if (this.edited == null) {
			return "";
		}

		String url = "";
		try {
			url = acAdd();
			// Сделано для защиты режимов от ошибок
			// Переменная режима должна выставляться после успешного выполнения
			// операции
			//opMode = OpMode.EDIT;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}

		return url;
	}
	
	
	protected String acAdd() throws Exception{
		sectionService.persist(edited);
		return this.retUrl;
	}
	
	

	public String edit() {
		try {
			this.edited = sectionService.find(((Section) selected).getId());
			this.mode = Mode.EDIT;
			return "/jsf/section/sectionOp.xhtml";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String cEdit(){
		if (this.edited == null) {
			return "";
		}

		String url = "";
		try {
			url = acEdit();
			// Сделано для защиты режимов от ошибок
			// Переменная режима должна выставляться после успешного выполнения
			// операции
			//opMode = OpMode.EDIT;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}

		return url;
	}
	
	
	protected String acEdit() throws Exception{
		sectionService.merge(edited);
		return this.retUrl;
	}
	
	

	public String del() {
		try {
			this.edited = sectionService.find(((Section) selected).getId());
			this.mode = Mode.DEL;
			return "/jsf/section/sectionOp.xhtml";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String cDel(){
		if (this.edited == null) {
			return "";
		}

		String url = "";
		try {
			url = acDel();
			// Сделано для защиты режимов от ошибок
			// Переменная режима должна выставляться после успешного выполнения
			// операции
			//opMode = OpMode.EDIT;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}

		return url;
	}
	
	
	protected String acDel() throws Exception{
		sectionService.remove(edited);
		return this.retUrl;
	}
	

}
