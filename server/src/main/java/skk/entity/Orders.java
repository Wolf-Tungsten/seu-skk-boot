package skk.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="orders")
public class Orders {

    public Orders(){

    }
    public Orders(String bvoId,String mvoId, String goodsId,String title, Integer price, Integer qty, String sku, Integer totalprice, String statestr, Integer state,String date) {
        this.bvoId = bvoId;
        this.mvoId = mvoId;
        this.title = title;
        this.price = price;
        this.qty = qty;
        this.sku = sku;
        this.totalprice = totalprice;
        this.statestr = statestr;
        this.state = state;
        this.date = date;
        this.goodsId = goodsId;
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

    @Column(name = "stateStr")
    public String statestr;
    //创建时间时间戳
    @Column(name = "createTime")
    public Integer createTimeStamp;

    //时间..
    @Column(name = "createdate")
    public String date;

    public String getStateStr(String role,int state){
        if (role.equals("MVO")){
            switch (state){
                case 1:
                    return "待支付";
                case 2:
                    return "待发货";
                case 3:
                    return "已发货";
                case 4:
                    return "已完成";
                case 5:
                    return "已取消";
            }

        }
        if (role.equals("BVO")){
            switch (state){
                case 1:
                    return "待支付";
                case 2:
                    return "已支付";
                case 3:
                    return "已发货";
                case  4:
                    return "已完成";
                case  5:
                    return "已取消";

            }
        }
        return " ";
    }

}
