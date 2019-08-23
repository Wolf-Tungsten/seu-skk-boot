package skk.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="goods")
public class Goods {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "price")
    public Integer price;

    @Column(name = "amount")
    public Integer amount;

    @Column(name = "sku")
    public String sku;

    @Column(name = "ediscription")
    public String edis;

    @Column(name = "adiscription")
    public String adis;
}
