package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Par")
public class Par {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name="parKey")
    public String parKey;

    @Column(name="parValue")
    public String parValue;

    @Column(name="parDescribe")
    public String parDescribe;
}
