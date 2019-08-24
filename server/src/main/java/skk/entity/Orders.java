package skk.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Orders {

    //对于借卖方而言的订单状态
    //订单处于待支付状态
    public final int READY_TO_PAY = 1;
    //订单处于已被支付状态
    public final int HAVE_BEEN_PAID = 2;
    //----------------------------------------------------------->
    //对于品牌商而言的订单状态
    //待发货状态
    public final int READY_TO_SHIP = 1;
    //已发货状态
    public final int HAVE_BEEN_SHIPPED = 2;
    //订单完成状态
    public final int COMPLIETE = 3;

    public Orders(){

    }
    public Orders(String bvoId,String mvoId, String title, Integer price, Integer qty, String sku, Integer totalprice, Integer state, String date) {
        this.bvoId = bvoId;
        this.mvoId = mvoId;
        this.title = title;
        this.price = price;
        this.qty = qty;
        this.sku = sku;
        this.totalprice = totalprice;
        this.state = state;
        this.date = date;
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 64)
    public String id;

    @Column(name = "mvoId")
    public String mvoId;


    @Column(name = "bvoId")
    public String bvoId;


    @Column(name = "goodsId")
    public String goodsId;

    //商品标题
    @Column(name = "title")
    public String title;

    //商品单价
    @Column(name = "price")
    public Integer price;

    //商品数量
    @Column(name = "QTY")
    public Integer qty;

   @Column(name = "sku")
    public String sku;

    //订单交易价格(总价格)
    @Column(name = "total")
    public Integer totalprice;

    //订单状态
    @Column(name = "state")
    public Integer state;

    //创建时间时间戳
    @Column(name = "createTime")
    public Integer createTimeStamp;

    //时间..
    @Column(name = "createdate")
    public String date;

}
