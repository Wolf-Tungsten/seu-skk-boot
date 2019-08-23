package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name ="OrderXMvo")
public class OrderXMvo {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "orderId")
    public String orderID;

    @Column(name="MvoId")
    public String MvoId;
}
