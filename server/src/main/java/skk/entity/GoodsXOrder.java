package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name ="GoodsXOrder")
public class GoodsXOrder {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "goodsId", nullable = false)
    public String goodsId;

    @Column(name="orderId", nullable = false)
    public String orderId;
}
