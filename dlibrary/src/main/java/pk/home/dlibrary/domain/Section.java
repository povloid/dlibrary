package pk.home.dlibrary.domain;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Section
 *
 */

@Entity
@Table(schema = "public", name = "section")
@NamedQueries({
	@NamedQuery(name = "Section.findAll", query = "select a from Section a order by a.id"),
	@NamedQuery(name = "Section.findByPrimaryKey", query = "select a from Section a where a.id = ?1")})
public class Section implements Serializable {

	   
	/**
	 * 
	 */
	private static final long serialVersionUID = -1536657556234217890L;


	@Column(nullable = false)
    @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Column(nullable = false,unique=true)
	private String keyName;
	
	@Length(max=500)
	@Column(length=500)
	private String description;

	public Section() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getKeyName() {
		return this.keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
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
		if (!(object instanceof Section)) {
			return false;
		}
		Section other = (Section) object;
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
