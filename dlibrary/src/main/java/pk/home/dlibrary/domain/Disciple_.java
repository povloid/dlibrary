package pk.home.dlibrary.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-10-15T21:50:55.869+0600")
@StaticMetamodel(Disciple.class)
public class Disciple_ {
	public static volatile SingularAttribute<Disciple, Long> id;
	public static volatile SingularAttribute<Disciple, String> fname;
	public static volatile SingularAttribute<Disciple, String> iname;
	public static volatile SingularAttribute<Disciple, String> lname;
	public static volatile SingularAttribute<Disciple, String> description;
	public static volatile SingularAttribute<Disciple, Date> wbdate;
}
