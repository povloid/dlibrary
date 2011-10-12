package pk.home.dlibrary.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-10-12T23:27:45.616+0600")
@StaticMetamodel(Book.class)
public class Book_ {
	public static volatile SingularAttribute<Book, Long> id;
	public static volatile SingularAttribute<Book, String> Title;
	public static volatile SingularAttribute<Book, String> description;
	public static volatile SingularAttribute<Book, Date> cdate;
}
