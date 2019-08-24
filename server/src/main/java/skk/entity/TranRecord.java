package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tranrecord")
public class TranRecord {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "operation", updatable = false)
    public String operation;

    @Column(name = "walletId", updatable = false)
    public String walletId;

    @Column(name = "cost", updatable = false)
    public int cost;

    @Column(name = "date", updatable = false)
    public Date date;

    @Column(name = "state")
    public int state; //0未审核 1审核并通过 2审核未通过

    @Column(name = "reason")
    public String reason;

    @Column(name = "memo")
    public String memo;

}
