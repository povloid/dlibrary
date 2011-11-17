package pk.home.dlibrary.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SingularAttribute;

import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pk.home.dlibrary.dao.AbstractBasicDAO.SortOrderType;
import pk.home.dlibrary.domain.Book;
import pk.home.dlibrary.domain.Book_;
import pk.home.dlibrary.domain.Genre;
import pk.home.dlibrary.domain.Section;
import pk.home.dlibrary.service.BookService;
import pk.home.dlibrary.service.GenreService;
import pk.home.dlibrary.service.SectionService;

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

	@Autowired
	private GenreService genreService;

	@Autowired
	private SectionService sectionService;

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
	protected List<Book> aload(LazyDataModel<Book> dataModel, int first,
			int pageSize, String sortField, SortOrder sortOrder,
			Map<String, String> filters, SortOrderType sot) throws Exception {

		SingularAttribute<Book, ?> orderByAttribute = Book_.id;
		if (sortField != null && sortField.equals("title")) {
			orderByAttribute = Book_.title;
		} else if (sortField != null && sortField.equals("section")) {
			orderByAttribute = Book_.section;
		} else if (sortField != null && sortField.equals("blocked")) {
			orderByAttribute = Book_.blocked;
		} else if (sortField != null && sortField.equals("reads")) {
			orderByAttribute = Book_.reads;
		} else if (sortField != null && sortField.equals("bookNumber")) {
			orderByAttribute = Book_.bookNumber;
		} 

		return bookService.getAllEntities(first,pageSize,  orderByAttribute,
				sot);
	}

	@Override
	protected String aAdd() throws Exception {
		this.edited = new Book();
		populateGenres();
		return "/jsf/book/bookOp.xhtml";
	}

	@Override
	protected String acAdd() throws Exception {
		saveGenres();
		bookService.persist(edited);
		return this.retUrl;
	}

	@Override
	protected String aEdit() throws Exception {
		this.edited = bookService.find(selected.getId());
		populateGenres();
		return "/jsf/book/bookOp.xhtml";
	}

	@Override
	protected String acEdit() throws Exception {
		saveGenres();
		bookService.merge(edited);
		return this.retUrl;
	}

	@Override
	protected String aDel() throws Exception {
		this.edited = bookService.find(selected.getId());
		populateGenres();
		return "/jsf/book/bookOp.xhtml";
	}

	@Override
	protected String acDel() throws Exception {
		bookService.remove(edited);
		return this.retUrl;
	}

	// Section
	// ------------------------------------------------------------------------------

	public List<Section> lSections() {

		try {
			List<Section> list = sectionService.getAllEntities();
			System.out.println(list.size());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public long getSectionId() {

		if (this.edited.getSection() != null)
			return this.edited.getSection().getId();
		else
			return 0;
	}

	public void setSectionId(long sectionId) {
		try {
			this.edited.setSection(sectionService.find(sectionId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Genre
	// ----------------------------------------------------------------------------------

	private DualListModel<String> genres;

	public DualListModel<String> getGenres() {
		return genres;
	}

	public void setGenres(DualListModel<String> genres) {
		this.genres = genres;
	}

	private Map<String, Long> genresMap = new HashMap<String, Long>();

	@Transactional
	private void populateGenres() {
		genresMap.clear();

		List<String> source = new ArrayList<String>();
		List<String> target = new ArrayList<String>();
		try {
			for (Genre rt : genreService.getAllEntities()) {
				source.add(rt.getKeyName());
				genresMap.put(rt.getKeyName(), rt.getId());
			}

			for (Genre rt : edited.getGenres()) {
				target.add(rt.getKeyName());
				genresMap.put(rt.getKeyName(), rt.getId());
			}

			source.removeAll(target);

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.genres = new DualListModel<String>(source, target);
	}

	private void saveGenres() throws Exception {
		edited.getGenres().clear();

		for (String s : genres.getTarget()) {
			long id = genresMap.get(s);
			Genre rt = genreService.find(id);
			edited.getGenres().add(rt);
		}

	}

}
