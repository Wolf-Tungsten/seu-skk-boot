package skk.controller;


import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import skk.entity.Goods;
import skk.entity.Orders;
import skk.entity.User;
import skk.repository.GoodsRepository;
import skk.repository.OrderRepository;
import skk.repository.UserRepository;
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
        for (int i=0; i<5; i++){
            int r = rand.nextInt(goodsList.size());
            Goods goods = goodsList.get(r);
            char c = 'b';
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
    @RequestMapping("/searchOrders")
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
        orders.statestr = ORDERS.getStateStr(user.role,reqBody.state);
        orderRepository.save(orders);
        return new SuccessResponse("订单状态修改成功，修改结果为："+orders.statestr);
    }
}

class changeOrdersStateReqBody{
    public String OrderId;
    public Integer state;
}