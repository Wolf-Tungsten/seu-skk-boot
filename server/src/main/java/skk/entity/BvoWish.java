package skk.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="bvowish")
public class BvoWish {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "bvoid")
    public String bvoid;

    @Column(name = "goodsid")
    public String goodsid;


}
