package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name ="OrderXBvo")
public class OrderXBvo {




    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "orderId")
    public String orderID;

    @Column(name="BvoId")
    public String BvoId;

    public OrderXBvo(String orderID,String BvoId){
        this.orderID = orderID;
        this.BvoId = BvoId;
    }
    public OrderXBvo(){

    }
}
