package skk.controller;


import org.aspectj.weaver.ast.Or;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.Goods;
import skk.entity.Orders;
import skk.entity.User;
import skk.entity.Wallet;
import skk.repository.GoodsRepository;
import skk.repository.OrderRepository;
import skk.repository.UserRepository;
import skk.repository.WalletRepository;
import skk.util.FailedResponse;
import skk.util.Response;
import skk.util.SuccessResponse;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
@RequestMapping("/Orders")
public class OrdersController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private WalletRepository walletRepository;
    public  Orders ORDERS = new Orders();
    User getUserInfo(String token){
        List<User> users = userRepository.findByToken(token);
        if(users.size() == 1){
            return users.get(0);
        } else {
            return null;
        }
    }
    //**随机添加五个订单
    @GetMapping
    @RequestMapping("/addOrder")
    public @ResponseBody
    Response addOrders(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token){
        User user = getUserInfo(token);
        if(user == null){
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }
        List<Goods> goodsList = goodsRepository.findAll();
        List<Orders> ordersList = new LinkedList<>();
        Random rand = new Random();
        char c = 'b';
        for (int i=0; i<5; i++){
            int r = rand.nextInt(goodsList.size());
            Goods goods = goodsList.get(r);
            Date date = new Date();
            Orders orders = new Orders(user.id,goods.mvoid,goods.id,goods.name,goods.price,
                    r*5,("GM"+(c++)),goods.price*r*5,ORDERS.getStateStr(user.role,1),1,date.toString());
            ordersList.add(orders);
            orderRepository.save(orders);
        }
        return new SuccessResponse("随机添加订单成功");
    }
    //**
    @GetMapping
    @RequestMapping("/searchAllOrders")
    public @ResponseBody
    Response showOrder(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token) {
        User user = getUserInfo(token);
        if (user == null) {
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }
        List<Orders> ordersList = new LinkedList<>();
        if (user.role.equals("MVO")) {
            ordersList = orderRepository.findAllByMvoId(user.id);
        } else if(user.role.equals("BVO")){
            ordersList = orderRepository.findAllByBvoId(user.id);
        }else {
            return new FailedResponse("权限不对");
        }
        for (int i =0;i<ordersList.size();i++){
            Orders od = ordersList.get(i);
            ordersList.get(i).statestr = ORDERS.getStateStr(user.role,od.state);
        }
        return new SuccessResponse(ordersList);
    }

    @PostMapping(path = "/changeOrdersState")
    public @ResponseBody
    Response changeOrderState(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
                              @RequestBody changeOrdersStateReqBody reqBody){
        User user = getUserInfo(token);
        if (user == null) {
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }
        Orders orders = orderRepository.findALLById(reqBody.OrderId);
        orders.state = reqBody.state;
        if(orders.state==2&&user.role.equals("BVO")){ //如果订单状态修改为已支付
            //已支付
            Wallet walletBvo = walletRepository.findAllByUserid(user.id).get(0);
            Wallet walletMvo = walletRepository.findAllByUserid(orders.mvoId).get(0);
            if(walletBvo.balance < reqBody.totalprice){
                return new FailedResponse("余额不足");
            }
            else{
                walletBvo.balance -= reqBody.totalprice;
                walletMvo.balance += reqBody.totalprice;
            }
        }

        orders.statestr = ORDERS.getStateStr(user.role,reqBody.state);
        orderRepository.save(orders);
        return new SuccessResponse("订单状态修改成功，修改结果为："+orders.statestr);
    }

    @GetMapping
    @RequestMapping("/searchOrdersByGoodsName")
    public @ResponseBody
    Response searchOrders(@RequestHeader(name = "x-skk-token", required = false , defaultValue = "null") String token,
                          @RequestParam String goodsName){
        User user = getUserInfo(token);
        if (user == null) {
            FailedResponse r = new FailedResponse("身份认证失效，请重新登录");
            return r;
        }
        String str = "%";
        str += goodsName;
        str += "%";
        //根据身份返回对应符合条件的订单
        if(user.role.equals("BVO")){
            List<Orders>ordersList = orderRepository.findAllByBvoIdAndTitleLike(user.id,str);
            return new SuccessResponse(ordersList);
        }else if (user.role.equals("MVO")){

            List<Orders> ordersList = orderRepository.findAllByMvoIdAndTitleLike(user.id,str);
            return new SuccessResponse(ordersList);
        }else{
            return new FailedResponse("权限错误");
        }
    }
}


class changeOrdersStateReqBody{
    public String OrderId;
    public Integer state;
    public Integer totalprice; //状态要修改为2时，用于钱包金额更新
}