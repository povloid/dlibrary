package pk.home.dlibrary.jsf;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author dev_sport
 * 
 * The basic CRUD functional class 
 *
 * @param <T>
 */
public abstract class AbstractBasicControl<T extends Object> {
	
	/**
	 * Control mode enum
	 * 
	 * @author dev_sport
	 * 
	 */
	protected enum Mode {
		VIEW, ADD, EDIT, DEL
	}

	/**
	 * Control mode
	 */
	protected Mode mode = Mode.ADD;

	public void setMode(String mode) {
		this.mode = Mode.valueOf(mode);
		System.out.println(">>>MODE: " + this.mode);
	}

	
	/**
	 * Get control mode
	 * @return
	 */
	public String getMode() {
		return this.mode.toString();
	}

	/**
	 * Is mode add?
	 * @return
	 */
	public boolean isAdd() {
		return mode == Mode.ADD;
	}

	/**
	 * Is mode edit?
	 * @return
	 */
	public boolean isEdit() {
		return mode == Mode.EDIT;
	}

	/**
	 * Is mode del?
	 * @return
	 */
	public boolean isDel() {
		return mode == Mode.DEL;
	}

	/**
	 * Selected bean
	 */
	protected T selected;

	/**
	 * Get selected bean;
	 * @return
	 */
	public T getSelected() {
		return selected;
	}

	/**
	 * Set selected bean
	 * @param selected
	 */
	public void setSelected(T selected) {
		this.selected = selected;
		System.out.println(">>>selected: " + this.selected);
	}

	/**
	 * Edited bean
	 */
	protected T edited;

	/**
	 * Get edited bean
	 * @return
	 */
	public T getEdited() {
		return edited;
	}

	/**
	 * Set edited bean
	 * @param edited
	 */
	public void setEdited(T edited) {
		this.edited = edited;
	}
 
	/**
	 * Return URL 
	 */
	protected String retUrl;
	
	/**
	 * Get returned url
	 * @return
	 */
	public String getRetUrl() {
		return retUrl;
	}

	/**
	 * Set returned url
	 * @param retUrl
	 */
	public void setRetUrl(String retUrl) {
		this.retUrl = retUrl + "?faces-redirect=true";
	}
	
	
	/**
	 * Action
	 * Cancel and return 
	 * @return
	 */
	public String cancel(){
		return this.retUrl;
	}
	
	
	/**
	 * View initialisation 
	 */
	public void init(){
		try {
			aInit();
			
			this.mode = Mode.VIEW;

		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}
	}
	
	
	/**
	 * View initialisation impl
	 * @throws Exception
	 */
	protected abstract void aInit() throws Exception;
	
	// LIST ------------------------------------------------------------------------
	
	/**
	 * Table list
	 */
	protected List<T> list;
	
	/**
	 * Get table list
	 * @return
	 */
	public List<T> getList() {
		return this.list;
	}
	
	
	// ADD -------------------------------------------------------------------------
	
	
	/**
	 * Action
	 * Add new bean
	 * @return
	 */
	public String add() {
		try {
			String url = aAdd();
			this.mode = Mode.ADD;
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}
		return null;
	}
	
	
	/**
	 * Add new bean impl
	 * @return
	 * @throws Exception
	 */
	protected abstract String aAdd() throws Exception;
	
	
	/**
	 * Action
	 * Conform add and return
	 * @return
	 */
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
	
	
	/**
	 * Conform add and return impl
	 * @return
	 * @throws Exception
	 */
	protected abstract String acAdd() throws Exception;
	
	
	// EDIT ----------------------------------------------------------------------------------------
	
	/**
	 * Action
	 * Edit bean
	 * @return
	 */
	public String edit() {
		try {
			String url = aEdit();
			this.mode = Mode.EDIT;
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}
		return null;
	}
	
	/**
	 * Edit bean impl
	 * @return
	 * @throws Exception
	 */
	protected abstract String aEdit() throws Exception;
	
	
	/**
	 * Action
	 * Conform edit bean and return
	 * @return
	 */
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
	
	/**
	 * Conform edit bean and return impl
	 * @return
	 * @throws Exception
	 */
	protected abstract String acEdit() throws Exception;
	
	// DEL -----------------------------------------------------------------------------------
	
	

	/**
	 * Action
	 * Delete bean
	 * @return
	 */
	public String del() {
		try {
			String url = aDel();
			this.mode = Mode.DEL;
			return url;
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: ", e
							.getMessage()));
		}
		return null;
	}
	
	/**
	 * Delete bean impl
	 * @return
	 * @throws Exception
	 */
	protected abstract String aDel() throws Exception;
	
	
	
	/**
	 * Action
	 * Conform delete bean end return
	 * @return
	 */
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

	
	/**
	 * Conform delete bean end return impl
	 * @return
	 * @throws Exception
	 */
	protected abstract String acDel() throws Exception;
}
