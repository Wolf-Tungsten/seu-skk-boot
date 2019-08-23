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

    @Column(name = "name")
    public String name;

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

    @Column(name = "model")
    public String model;

    @Column(name = "maintain")
    public String maintain;

    @Column(name = "upc")
    public String upc;

    @Column(name = "ena")
    public String ena;
}
