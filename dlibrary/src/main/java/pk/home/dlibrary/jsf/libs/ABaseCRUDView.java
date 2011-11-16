package pk.home.dlibrary.jsf.libs;

import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Only View class
 * 
 * @author povloid
 * 
 */
public abstract class ABaseCRUDView<T extends Object> {

	// ----------------------------------------------------------------------------------------------------------------
	// INIT

	/**
	 * View initialisation
	 */
	public void init() {
		try {
			aInit();
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
	 * 
	 * @throws Exception
	 */
	protected abstract void aInit() throws Exception;

	// -- bean params
	// -------------------------------------------------------------------------------------------------

	protected T selected;
	protected T edited;

	// ----------------------------------------------------------------------------------------------------------------

	// request params
	private Integer prows = 10;
	private Integer ppage;
	private String pcsortOrder;
	private String pcsortField;

	// session bean params
	private Integer rows = 10;
	private Integer page = 1;
	private String csortOrder;
	private String csortField;

	/**
	 * prepere params
	 */
	private void prepereParams() {
		rows = prows != null ? prows : rows;
		prows = null;

		page = ppage != null ? ppage : page;
		ppage = null;

		csortOrder = pcsortOrder != null ? pcsortOrder : csortOrder;
		csortOrder = null;

		csortField = pcsortField != null ? pcsortField : csortField;
		csortField = null;
	}

	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * Lazy model
	 */
	private LazyDataModel<T> dataModel = new LazyDataModel<T>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4256244307632901637L;

		@Override
		public List<T> load(int first, int pageSize, String sortField,
				SortOrder sortOrder, Map<String, String> filters) {

			System.out.println("first=" + first + ", pageSize=" + pageSize
					+ ", sortField=" + sortField + ", sortOrder=" + sortOrder
					+ ", filters=" + filters);

			try {

				// Prepere request params
				prepereParams();

				// set size ----------------------------------------------------
				int size = aloadCount().intValue();
				dataModel.setRowCount(size);

				if (size < pageSize)
					page = 1; // Коррекция при убывании

				// page
				if (page != null) { // Здесь уже подстройка запроса под page

					System.out.println(">>>page 1 do page: " + page);
					first = pageSize * page.intValue() - pageSize;
					first = first >= 0 ? first : 0;
				}

				// call child loading impl -------------------------------------
				List<T> l = aload(this, first, pageSize, sortField, sortOrder,
						filters);

				System.out.println(l.size());

				return l;
			} catch (Exception e) {
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Error: ", e.getMessage()));
			}
			return null;
		}
	};

	public LazyDataModel<T> getDataModel() {
		return dataModel;
	}

	public void setDataModel(LazyDataModel<T> dataModel) {
		this.dataModel = dataModel;
	}

	/**
	 * Get rows count
	 * 
	 * @return
	 * @throws Exception
	 */
	protected abstract Long aloadCount() throws Exception;

	/**
	 * Load the data model
	 * 
	 * @param dataModel
	 * @param first
	 * @param pageSize
	 * @param sortField
	 * @param sortOrder
	 * @param filters
	 * @return
	 * @throws Exception
	 */
	protected abstract List<T> aload(LazyDataModel<T> dataModel, int first,
			int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters) throws Exception;

	// getters and setters
	// ---------------------------------------------------------------------------------------------

	public Integer getProws() {
		return prows;
	}

	public void setProws(Integer prows) {
		this.prows = prows;
	}

	public Integer getPpage() {
		return ppage;
	}

	public void setPpage(Integer ppage) {
		this.ppage = ppage;
	}

	public String getPcsortOrder() {
		return pcsortOrder;
	}

	public void setPcsortOrder(String pcsortOrder) {
		this.pcsortOrder = pcsortOrder;
	}

	public String getPcsortField() {
		return pcsortField;
	}

	public void setPcsortField(String pcsortField) {
		this.pcsortField = pcsortField;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getCsortOrder() {
		return csortOrder;
	}

	public void setCsortOrder(String csortOrder) {
		this.csortOrder = csortOrder;
	}

	public String getCsortField() {
		return csortField;
	}

	public void setCsortField(String csortField) {
		this.csortField = csortField;
	}

	public T getSelected() {
		return selected;
	}

	public void setSelected(T selected) {
		this.selected = selected;
	}

	public T getEdited() {
		return edited;
	}

	public void setEdited(T edited) {
		this.edited = edited;
	}

}
