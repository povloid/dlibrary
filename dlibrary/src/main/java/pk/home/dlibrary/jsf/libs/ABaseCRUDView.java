package pk.home.dlibrary.jsf.libs;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
			prepereParams();
			this.allRowsCount = initAllRowsCount();
			calculateBeanParams();
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

	// ----------------------------------------------------------------------------------------------------------------

	// request params
	private Integer prows = 10;
	private Integer ppage;
	private String pcsortOrder;
	private String pcsortField;

	// session bean params
	protected Integer rows = 10;
	protected Integer page = 1;
	protected String csortOrder;
	protected String csortField;

	// calculate bean params
	protected int allPagesCount;
	protected long allRowsCount;
	

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

	/**
	 * Calculate beans params
	 */
	private void calculateBeanParams() {
		if (rows == 0) {
			allPagesCount = 0;
			oPButtons = null;
		} else {
			allPagesCount = (int) (allRowsCount / rows);
			allPagesCount = allRowsCount > 0 && (allRowsCount % rows) > 0 ? allPagesCount + 1
					: allPagesCount;
			
			
			int part = maxOPButtons / 2;
			int ibegin = page - part < 1 ? 1: page - part;
			int iend= page + part > allPagesCount ? allPagesCount : page + part;
			
			
			
			oPButtons = new ArrayList<OrderingPaginateButton>();
			for(int i=ibegin ; i < iend + 1; i++){
				oPButtons.add(new OrderingPaginateButton(i + "", i + ""));
			}
		}
		
		
		
		
		
	}

	
	
	/**
	 * Initialize all rows in data
	 * 
	 * @return
	 */
	protected abstract long initAllRowsCount() throws Exception;
	
	
	public static final int maxOPButtons = 10;
	
	private List<OrderingPaginateButton> oPButtons;

	// ----------------------------------------------------------------------------------------------------------------
	// Simple table render

	protected List<T> dataModel = new ArrayList<T>();

	// ---------------------------------------------------------------------------------------------
	// getters and setters
	
	
	
	
	

	public Integer getProws() {
		return prows;
	}

	public List<OrderingPaginateButton> getoPButtons() {
		return oPButtons;
	}

	public int getAllPagesCount() {
		return allPagesCount;
	}

	public long getAllRowsCount() {
		return allRowsCount;
	}

	public List<T> getDataModel() {
		return dataModel;
	}

	public void setDataModel(List<T> dataModel) {
		this.dataModel = dataModel;
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

}
