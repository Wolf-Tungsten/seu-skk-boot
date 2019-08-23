package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "DD")
public class DD {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name="entryType")
    public String entryType;

    @Column(name="describe")
    public String describe;

    @Column(name="code")
    public String code;

    @Column(name="entryValue")
    public String entryValue;
}