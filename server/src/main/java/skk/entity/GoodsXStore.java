package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name ="GoodsXStore")
public class GoodsXStore {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "goodsId", nullable = false)
    String goodsId;

    @Column(name="storeId", nullable = false)
    String storeId;
}
