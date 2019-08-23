package skk.entity;

import com.sun.org.apache.xpath.internal.objects.XString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "DD")
public class DD {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String Id;

    @Column(name="type")
    String type;

    @Column(name="describe")
    String describe;

    @Column(name="code")
    String code;

    @Column(name="value")
    String value;
}