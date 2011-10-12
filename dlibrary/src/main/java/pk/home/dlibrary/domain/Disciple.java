package pk.home.dlibrary.domain;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Disciple
 *
 */
@Entity
@Table(schema = "public", name = "disciple")
@NamedQueries({
	@NamedQuery(name = "Disciple.findAll", query = "select a from Disciple a order by a.id"),
	@NamedQuery(name = "Disciple.findByPrimaryKey", query = "select a from Disciple a where a.id = ?1")})
public class Disciple implements Serializable {
	   
	/**
	 * 
	 */
	private static final long serialVersionUID = 2810744562969016570L;

		
	@Column(nullable = false)
    @Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Column(nullable = false)
	private String fname;
	@NotNull
    @Column(nullable = false)
	private String iname;
	private String lname;
	
	@Length(max=500)
	@Column(length=500)
	private String description;
	
	@Temporal(TemporalType.DATE)
	private Date wbdate;


	public Disciple() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}   
	public String getIname() {
		return this.iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}   
	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public Date getWbdate() {
		return this.wbdate;
	}

	public void setWbdate(Date wbdate) {
		this.wbdate = wbdate;
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
		if (!(object instanceof Disciple)) {
			return false;
		}
		Disciple other = (Disciple) object;
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
