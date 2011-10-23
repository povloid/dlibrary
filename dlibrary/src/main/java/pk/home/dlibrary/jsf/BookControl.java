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
import pk.home.dlibrary.domain.Book;
import pk.home.dlibrary.domain.Book_;
import pk.home.dlibrary.service.BookService;

/**
 * 
 * @author traveler
 */

@Scope("session")
@Component("bookControl")
public class BookControl extends AbstractBasicControl<Book> implements
		Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1481201000229334509L;

	@Autowired
	private BookService bookService;

	@Override
	public void aInit() throws Exception {
		
	}
	
	@Override
	protected void aUpdate() throws Exception {
		
	}
	
	
	
	@Override
	protected Long aloadCount() throws Exception {
		return bookService.count();
	}
	
	
	@Override
	protected List<Book> aload(LazyDataModel<Book> dataModel, int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters, SortOrderType sot) throws Exception {
		
		SingularAttribute<Book, ?> orderByAttribute = Book_.id;
		if(sortField != null && sortField.equals("title")){
			orderByAttribute = Book_.Title;
		}
		
		return bookService.getAllEntities(pageSize,first, orderByAttribute, sot);
	}
	

	@Override
	protected String aAdd() throws Exception {
		this.edited = new Book();
		return "/jsf/book/bookOp.xhtml";
	}

	@Override
	protected String acAdd() throws Exception {
		bookService.persist(edited);
		return this.retUrl;
	}

	@Override
	protected String aEdit() throws Exception {
		this.edited = bookService.find(selected.getId());
		return "/jsf/book/bookOp.xhtml";
	}

	@Override
	protected String acEdit() throws Exception {
		bookService.merge(edited);
		return this.retUrl;
	}

	@Override
	protected String aDel() throws Exception {
		this.edited = bookService.find(selected.getId());
		return "/jsf/book/bookOp.xhtml";
	}

	@Override
	protected String acDel() throws Exception {
		bookService.remove(edited);
		return this.retUrl;
	}

	

	

	

}
