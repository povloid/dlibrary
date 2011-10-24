package pk.home.dlibrary.domain;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.validator.constraints.Length;

import pk.home.dlibrary.domain.Book;
import pk.home.dlibrary.domain.BookOrder;

/**
 * Entity implementation class for Entity: Item
 * 
 */
@Entity
@Table(schema = "public", name = "item")
@NamedQueries({
		@NamedQuery(name = "Item.findAll", query = "select a from Item a order by a.id"),
		@NamedQuery(name = "Item.findByPrimaryKey", query = "select a from Item a where a.id = ?1") })
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1378581878147294245L;

	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private BookOrder bookOrder;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Book book;

	@Temporal(TemporalType.DATE)
	private Date cldate;

	@Length(max = 500)
	@Column(length = 500)
	private String description;

	public Item() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BookOrder getBookOrder() {
		return bookOrder;
	}

	public void setBookOrder(BookOrder bookOrder) {
		this.bookOrder = bookOrder;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getCldate() {
		return this.cldate;
	}

	public void setCldate(Date cldate) {
		this.cldate = cldate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// not set
		if (!(object instanceof Item)) {
			return false;
		}
		Item other = (Item) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Section[ id=" + id + " ]";
	}

}
