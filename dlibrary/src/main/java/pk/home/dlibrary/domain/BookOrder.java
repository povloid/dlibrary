package pk.home.dlibrary.domain;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Order
 * 
 */
@Entity
@Table(schema = "public", name = "bookorder")
@NamedQueries({
		@NamedQuery(name = "BookOrder.findAll", query = "select a from BookOrder a order by a.id"),
		@NamedQuery(name = "BookOrder.findByPrimaryKey", query = "select a from BookOrder a where a.id = ?1") })
public class BookOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4897282774695408442L;

	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date cdate;

	@Length(max = 500)
	@Column(length = 500)
	private String description;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Disciple disciple;

	@Column(nullable = false)
	@NotNull
	private boolean closed;

	@OneToMany(mappedBy = "bookOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Item> items = new ArrayList<Item>();

	public BookOrder() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCdate() {
		return this.cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Disciple getDisciple() {
		return disciple;
	}

	public void setDisciple(Disciple disciple) {
		this.disciple = disciple;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
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
		if (!(object instanceof BookOrder)) {
			return false;
		}
		BookOrder other = (BookOrder) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Disciple[ id=" + id + " ]";
	}
}
