package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="tranRecord")
public class TranRecord {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "from")
    public String fromId;

    @Column(name = "to")
    public String toId;

    @Column(name = "cost")
    public Integer cost;

}
