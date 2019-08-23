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

    @Column(name = "mvoid")
    public String mvoid;

    @Column(name = "name")
    public String name;

    @Column(name = "price")
    public Integer price;

    @Column(name = "length")
    public Integer length;

    @Column(name = "width")
    public Integer width;

    @Column(name = "height")
    public Integer height;

    @Column(name = "weight")
    public Integer weight;

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

    //商品类别
    @Column(name = "type")
    public String type;
    //入库状态
    @Column(name = "state")
    public Integer state; // 1 待入库 ; 2 入库中; 3 待上架; 4 上架中
    //主图
    @Column(name ="mimg",length = 1024000)
    public String img;
}
