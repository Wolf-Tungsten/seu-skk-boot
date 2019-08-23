package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.*;
import skk.repository.*;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


class  BvoInfochangeReqBody{
    public String name;
    public String email;
    public String phone;
    public String userid;
}
//添加心愿单项目表单
class  BvoWishReqBody{
    public String bvoId;
    public String goodsId;
}
//删除心愿单项目表单
class deletWishsReqBody{
    public String bvoId;
    public List<String> goodsIdList ;
}

//支付请求表单
class payOrderReqBody{
    public String goodsId;
    public String reciever;
    public String detialAddress;
    public String address_state;
    public String address_city;
    public String OrderUid;
    public Integer ShippingMethod;
}



@RestController
@CrossOrigin
@RequestMapping("/bvo")
public class Bvocontroller {
    @Autowired
    private BvoRepository bvoRepository;
    @Autowired
    private BvowishRepository bvowishRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderXBvoRepository orderXBvoRepository;


    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @RequestMapping("/updatebvo")
    public @ResponseBody
    Response updateBvo (@RequestBody BvoInfochangeReqBody requestBody) {
        //各种判断
        Bvo newBvo = new Bvo();
        newBvo.name = requestBody.name;
        newBvo.email = requestBody.email;
        newBvo.phone = requestBody.phone;
        newBvo.userid = requestBody.userid;
        bvoRepository.save(newBvo);
        return new SuccessResponse("success");
    }
    @GetMapping
    @RequestMapping("/findAllBvo")
    public @ResponseBody
    Response finAllBvo(){
        List<Bvo> bl = new LinkedList<>();
        Iterable<Bvo> bvoIterable = bvoRepository.findAll();
        Iterator<Bvo> itr = bvoIterable.iterator();
        while (itr.hasNext()){
            bl.add(itr.next());
        }
        System.out.println(bl.size());
        return new SuccessResponse(bl);
    }
    //402881e56cbe39d6016cbe3ac41d0000 人
    //8a8a8b716cbd4fc7016cbd52162b0002 商品
    //8a8a8b716cbd4fc7016cbd5209b60001
    //**添加商品到心愿单
    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @RequestMapping("/addWish")
    public @ResponseBody
    Response addWish(@RequestBody BvoWishReqBody reqBody){
        BvoWish w = new BvoWish();
        w.bvoid = reqBody.bvoId;
        w.goodsid = reqBody.goodsId;
        bvowishRepository.save(w);
        return new SuccessResponse("success");

    }
    //**批量删除心愿单中的商品
    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @RequestMapping("/deleteWishs")
        public @ResponseBody
        Response deleteWishs(@RequestBody deletWishsReqBody reqBody){
            List<String> goodIdList = reqBody.goodsIdList;
            System.out.println(goodIdList.size());
            for (int i = 0;i < goodIdList.size(); i++){
                bvowishRepository.deleteAllByBvoidAndGoodsid(reqBody.bvoId,goodIdList.get(i));
            }
        return new SuccessResponse("deleteSuccess");
    }
//**显示心愿单
    @GetMapping
    @RequestMapping("/showWishList")
    public @ResponseBody
    Response showWishList(){
        Iterable<BvoWish> wishlist = bvowishRepository.findAll();
        Iterator<BvoWish> itr = wishlist.iterator();
        List<Goods> goodsList = new LinkedList<>();
        while (itr.hasNext()){
            BvoWish bw = itr.next();
            Goods ng = goodsRepository.findAllById(bw.goodsid);
            goodsList.add(ng);
        }
        return new SuccessResponse(goodsList);
    }
//**商品浏览
    @GetMapping
    @RequestMapping("/showAllGoods")
    public @ResponseBody
    Response showGoods(){
        Iterable<Goods> goodsIterable = goodsRepository.findAll();
        Iterator<Goods> itr = goodsIterable.iterator();
        List<Goods> goodsList = new LinkedList<>();
        while (itr.hasNext()){
            goodsList.add(itr.next());
        }
        return new SuccessResponse(goodsList);
    }

    //**初始化一些amazon和ebay上用户的订单
    @GetMapping
    @RequestMapping("/initOrders")
    public @ResponseBody
    Response buyGoods(){
        String userId = "";
        Order newOrder1 = new Order(userId,"电炖锅",60,50,"QK123456",
                3000,1,"2017-23-02 19:00:20");
        Order newOrder2 = new Order(userId,"李永乐线代讲义",10,50,"QK123456",
                500,1,"2017-23-02 19:00:20");
        Order newOrder3 = new Order(userId,"李永乐线代讲义",10,50,"QK123456",
                500,1,"2017-23-02 19:00:20");
        orderRepository.save(newOrder1);
        orderRepository.save(newOrder2);
        orderRepository.save(newOrder3);
        Iterable<Order> orderIterable = orderRepository.findAll();
        Iterator<Order> itr = orderIterable.iterator();
        while (itr.hasNext()){
            OrderXBvo oxb = new OrderXBvo(userId,itr.next().id);
            orderXBvoRepository.save(oxb);
        }
        return new SuccessResponse("initSuccess");
    }

    //借卖方订单支付
    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @RequestMapping("/payOrder")
    public @ResponseBody
    Response payOrder(@RequestBody payOrderReqBody reqBody){
        String orderUid = reqBody.OrderUid;
        String goodsUid = reqBody.goodsId;
        Order bvoOrder = orderRepository.findAllById(orderUid);
        Goods goods = goodsRepository.findAllById(goodsUid);
        Order mvoOrder = new Order(goods.mvoid,bvoOrder.title,bvoOrder.price,
                bvoOrder.qty,bvoOrder.sku,bvoOrder.totalprice,bvoOrder.state,bvoOrder.date);
        orderRepository.save(mvoOrder);
        return new SuccessResponse("success");
    }

}

