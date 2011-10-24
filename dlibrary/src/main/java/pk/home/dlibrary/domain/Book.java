package pk.home.dlibrary.domain;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Book
 * 
 */
@Entity
@Table(schema = "public", name = "book")
@NamedQueries({
		@NamedQuery(name = "Book.findAll", query = "select a from Book a order by a.id"),
		@NamedQuery(name = "Book.findByPrimaryKey", query = "select a from Book a where a.id = ?1") })
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1693703599699372298L;

	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true, nullable = false)
	private String Title;

	@Length(max = 500)
	@Column(length = 500)
	private String description;

	@Length(max = 200)
	@Column(length = 200)
	private String author;

	@Length(max = 200)
	@Column(length = 200)
	private String publishingHouse;

	private Integer cyear;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private List<Genre> genres = new ArrayList<Genre>();

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Section section;

	boolean blocked;

	public Book() {
		super();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.Title;
	}

	public void setTitle(String Title) {
		this.Title = Title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCyear() {
		return cyear;
	}

	public void setCyear(Integer cyear) {
		this.cyear = cyear;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(String publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
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
		if (!(object instanceof Book)) {
			return false;
		}
		Book other = (Book) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Book[ id=" + id + " ]";
	}

	public String getGenresText() {
		String text = "";
		for (Genre g : genres) {
			text += g.getKeyName() + "; ";
		}
		return text;
	}

}
