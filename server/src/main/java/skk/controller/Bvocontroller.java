package skk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.*;
import skk.repository.*;
import skk.util.FailedResponse;
import skk.util.Response;
import skk.util.SuccessResponse;

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
    public String goodsId;
}
//删除心愿单项目表单
class deletWishsReqBody{
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
    @Autowired
    private UserRepository userRepository;

    User getUserInfo(String token){
        List<User> users = userRepository.findByToken(token);
        if(users.size() == 1){
            return users.get(0);
        } else {
            return null;
        }
    }

    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @RequestMapping("/updatebvo")
    public @ResponseBody
    Response updateBvo (@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,@RequestBody BvoInfochangeReqBody requestBody) {

        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        //各种判断
        Bvo newBvo = new Bvo();
        newBvo.name = requestBody.name;
        newBvo.email = requestBody.email;
        newBvo.phone = requestBody.phone;
        newBvo.userid = user.id;
        bvoRepository.save(newBvo);
        return new SuccessResponse("success");
    }
    @GetMapping
    @RequestMapping("/findAllBvo")
    public @ResponseBody
    Response finAllBvo(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token){
        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }
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
    Response addWish(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,@RequestBody BvoWishReqBody reqBody){
        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }
        BvoWish w = new BvoWish();
        w.bvoid = user.id;
        w.goodsid = reqBody.goodsId;
        bvowishRepository.save(w);
        return new SuccessResponse("success");

    }
    //**批量删除心愿单中的商品
    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @RequestMapping("/deleteWishs")
        public @ResponseBody
        Response deleteWishs(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,@RequestBody deletWishsReqBody reqBody){
        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }
        List<String> goodIdList = reqBody.goodsIdList;
        System.out.println(goodIdList.size());
        for (int i = 0;i < goodIdList.size(); i++){
            bvowishRepository.deleteAllByBvoidAndGoodsid(user.id,goodIdList.get(i));
        }
        return new SuccessResponse("deleteSuccess");
    }
//**显示心愿单
    @GetMapping
    @RequestMapping("/showWishList")
    public @ResponseBody
    Response showWishList(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token){
        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }

        List<BvoWish> wishList = bvowishRepository.findAllByBvoid(user.id);
        List<Goods> goodsList = new LinkedList<>();
        for (int i=0;i<goodsList.size();i++){
            Goods goods = goodsRepository.findAllById(wishList.get(i).goodsid);
            goodsList.add(goods);
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
//        String userId = "";
//        Orders newOrder1 = new Orders(userId,"电炖锅",60,50,"QK123456",
//                3000,1,"2017-23-02 19:00:20");
//        Orders newOrder2 = new Orders(userId,"李永乐线代讲义",10,50,"QK123456",
//                500,1,"2017-23-02 19:00:20");
//        Orders newOrder3 = new Orders(userId,"李永乐线代讲义",10,50,"QK123456",
//                500,1,"2017-23-02 19:00:20");
//        orderRepository.save(newOrder1);
//        orderRepository.save(newOrder2);
//        orderRepository.save(newOrder3);
//        Iterable<Orders> orderIterable = orderRepository.findAll();
//        Iterator<Orders> itr = orderIterable.iterator();
//        while (itr.hasNext()){
//            OrderXBvo oxb = new OrderXBvo(userId,itr.next().id);
//            orderXBvoRepository.save(oxb);
//        }
        return new SuccessResponse("initSuccess");
    }
    //借卖方订单显示
    @GetMapping
    @RequestMapping("/showOrder")
    public @ResponseBody
    Response showOrders(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token){
//        User user = getUserInfo(token);
//        if(user == null){
//            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
//            return r;
//        }
//        List<Orders> orderList = orderRepository.findAllByUserId(user.id);
        return new SuccessResponse("success");
    }

    //借卖方订单支付
    @PostMapping(consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @RequestMapping("/payOrder")
    public @ResponseBody
    Response payOrder(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,@RequestBody payOrderReqBody reqBody){
//        User user = getUserInfo(token);
//        if(user == null){
//            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
//            return r;
//        }
//        String orderUid = reqBody.OrderUid;
//        String goodsUid = reqBody.goodsId;
//        Orders bvoOrder = orderRepository.findAllById(orderUid);
//        Goods goods = goodsRepository.findAllById(goodsUid);
//        Orders mvoOrder = new Orders(goods.mvoid,bvoOrder.title,bvoOrder.price,
//                bvoOrder.qty,bvoOrder.sku,bvoOrder.totalprice,bvoOrder.state,bvoOrder.date);
//        orderRepository.save(mvoOrder);
        return new SuccessResponse("success");
    }
    //
}

