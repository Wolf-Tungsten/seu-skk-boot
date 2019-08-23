package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name ="GoodsXWishList")
public class GoodsXWishList {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "goodsId", nullable = false)
    public String goodsId;

    @Column(name="wishListId", nullable = false)
    public String wishListId;
}
