package pk.home.dlibrary.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-10-15T21:50:55.825+0600")
@StaticMetamodel(Book.class)
public class Book_ {
	public static volatile SingularAttribute<Book, Long> id;
	public static volatile SingularAttribute<Book, String> Title;
	public static volatile SingularAttribute<Book, String> description;
	public static volatile SingularAttribute<Book, String> author;
	public static volatile SingularAttribute<Book, String> publishingHouse;
	public static volatile SingularAttribute<Book, Date> cdate;
	public static volatile ListAttribute<Book, Genre> genres;
	public static volatile SingularAttribute<Book, Section> section;
	public static volatile SingularAttribute<Book, Boolean> removed;
}
