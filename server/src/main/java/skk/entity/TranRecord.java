package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="tranrecord")
public class TranRecord {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "fromId")
    public String fromId;

    @Column(name = "toId")
    public String toId;

    @Column(name = "cost")
    public int cost;

    @Column(name = "date")
    public String date;

    @Column(name = "state")
    public int state; //0未审核 1审核未通过 2审核并通过

    @Column(name = "reason")
    public String reason;

    @Column(name = "memo")
    public String memo;

}
